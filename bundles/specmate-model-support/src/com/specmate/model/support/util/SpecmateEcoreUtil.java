package com.specmate.model.support.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.CDOState;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Iterators;
import com.specmate.common.AssertUtil;
import com.specmate.common.UUIDUtil;
import com.specmate.common.exception.SpecmateException;
import com.specmate.common.exception.SpecmateInternalException;
import com.specmate.model.administration.ErrorCode;
import com.specmate.model.base.BasePackage;
import com.specmate.model.base.Folder;
import com.specmate.model.base.IContainer;
import com.specmate.model.base.IContentElement;
import com.specmate.model.base.IRecycled;
import com.specmate.model.base.ModelImage;
import com.specmate.model.testspecification.TestProcedure;
import com.specmate.model.testspecification.TestStep;

public class SpecmateEcoreUtil {

	public static void copyAttributeValues(EObject source, EObject target) {
		AssertUtil.preTrue(target.getClass().isAssignableFrom(source.getClass()));
		for (EAttribute attribute : target.eClass().getEAllAttributes()) {
			target.eSet(attribute, source.eGet(attribute));
		}
	}

	public static void setAttributeValue(EObject target, Object newValue, String attribute) {
		target.eSet(target.eClass().getEStructuralFeature(attribute), newValue);
	}

	public static EObject getParent(EObject target) {
		return target.eContainer();
	}

	public static LinkedList<EObject> getAllParents(EObject element) {
		LinkedList<EObject> parentsList = new LinkedList<EObject>();

		EObject parent = SpecmateEcoreUtil.getParent(element);
		while (parent != null && !SpecmateEcoreUtil.isProject(parent)) {
			parentsList.add(parent);
			parent = SpecmateEcoreUtil.getParent(parent);
		}
		return parentsList;
	}

	public static void updateParentsOnRestore(EObject firstParent) {
		if (firstParent == null) {
			return;
		}
		LinkedList<EObject> parentsList = SpecmateEcoreUtil.getAllParents(firstParent);
		parentsList.addFirst(firstParent);
		while (parentsList.size() > 0) {
			EObject parent = parentsList.pop();
			TreeIterator<EObject> contents = ((EObject) parent).eAllContents();
			Iterator<EObject> filtered;
			filtered = Iterators.filter(contents, o -> ((IRecycled) o).isRecycled());
			if (filtered.hasNext()) {
				if (parent instanceof IRecycled) {
					((IRecycled) parent).setHasRecycledChildren(true);
				}
				while (parentsList.size() > 0) {
					EObject ancestor = parentsList.pop();
					if (ancestor instanceof IRecycled) {
						((IRecycled) ancestor).setHasRecycledChildren(true);
					}
				}
				return;
			} else {
				if (parent instanceof IRecycled) {
					((IRecycled) parent).setHasRecycledChildren(false);
				}
			}
		}
	}

	public static void copyReferences(EObject source, EObject target) {
		AssertUtil.preTrue(target.getClass().isAssignableFrom(source.getClass()));
		for (EReference reference : target.eClass().getEAllReferences()) {
			if (!reference.isContainment()) {
				target.eSet(reference, source.eGet(reference));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends EObject> T shallowCopy(T source) {
		ShallowCopier copier = new ShallowCopier();
		return (T) copier.copy(source);
	}

	public static EObject getEObjectWithId(String id, List<? extends EObject> objects) {
		for (EObject object : objects) {
			String currentId = SpecmateEcoreUtil.getID(object);
			if (currentId != null && currentId.equals(id)) {
				return object;
			}
		}
		return null;
	}

	public static EObject getEObjectWithName(String name, List<? extends EObject> objects) {
		for (EObject object : objects) {
			String currentName = SpecmateEcoreUtil.getName(object);
			if (currentName != null && currentName.equals(name)) {
				return object;
			}
		}
		return null;
	}

	public EObject getFirstRoot(Resource resource) {
		return resource.getContents().get(0);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObjectByUriFragment(Resource resource, String id, Class<T> clazz) {
		EObject object = resource.getEObject(id);
		if (object != null & clazz.isAssignableFrom(object.getClass())) {
			return (T) object;
		} else {
			return null;
		}
	}

	public static <T> List<T> getRootObjectsByType(Resource resource, Class<T> clazz) {
		return pickInstancesOf(resource.getContents(), clazz);
	}

	public static <T> Set<T> uniqueInstancesOf(List<? extends EObject> contents, Class<T> clazz) {
		Set<T> result = new HashSet<>();
		result.addAll(pickInstancesOf(contents.iterator(), clazz));
		return result;
	}

	public static <T> List<T> pickInstancesOf(List<? extends EObject> contents, Class<T> clazz) {
		return pickInstancesOf(contents.iterator(), clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> pickInstancesOf(Iterator<? extends EObject> contents, Class<T> clazz) {
		ArrayList<T> list = new ArrayList<>();
		while (contents.hasNext()) {
			EObject content = contents.next();
			if (clazz.isAssignableFrom(content.getClass())) {
				list.add((T) content);
			}
		}
		return list;
	}

	public static <T> T getAttributeValue(EObject object, String featureName, Class<T> clazz) {
		EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
		if (feature != null && feature instanceof EAttribute) {
			Object result = object.eGet(feature);
			if (result == null) {
				return null;
			}
			if (clazz.isAssignableFrom(result.getClass())) {
				return clazz.cast(result);
			}
		}
		return null;
	}

	public static String getName(EObject object) {
		return getAttributeValue(object, "name", String.class);
	}

	public static String getDescription(EObject object) {
		return getAttributeValue(object, "description", String.class);
	}

	/**
	 * @return The id ob <code>object</code> or <code>null</code> if object does not
	 *         have any id
	 */
	public static String getID(EObject object) {
		return getAttributeValue(object, "id", String.class);
	}

	public static void unsetAllReferences(EObject object, Collection<EStructuralFeature> keepFeatures) {
		for (EStructuralFeature feature : object.eClass().getEAllStructuralFeatures()) {
			if (!keepFeatures.contains(feature)) {
				if (feature instanceof EReference) {
					EReference reference = (EReference) feature;
					if (!reference.isContainment() && !reference.isContainer()) {
						object.eUnset(feature);
					}
				}
			}
		}
	}

	public static void unsetAllReferences(EObject object) {
		unsetAllReferences(object, Collections.emptyList());
	}

	public static String getIdForChild() {
		return UUIDUtil.generateUUID();
	}

	public static String getNameForChild(IContainer parent, EClass type) {
		int i = 1;
		String format = "%s-%d";
		EList<IContentElement> contents = parent.getContents();
		String candidate;
		do {
			candidate = String.format(format, type.getName(), i);
			i++;
		} while (getEObjectWithName(candidate, contents) != null);

		return candidate;
	}

	public static void detach(EObject object, Collection<EStructuralFeature> keepFeatures) {
		Iterator<EObject> childIterator = EcoreUtil.getAllContents(object, false);
		while (childIterator.hasNext()) {
			EObject next = childIterator.next();
			unsetAllReferences(next, keepFeatures);
		}
		unsetAllReferences(object, keepFeatures);
		EcoreUtil.remove(object);
	}

	public static void detach(EObject object) {
		detach(object, Collections.emptyList());
	}

	public static <T> T getFirstAncestorOfType(EObject object, Class<T> clazz) {
		EObject ancestor = object;
		while (ancestor != null) {
			ancestor = ancestor.eContainer();
			if (clazz.isAssignableFrom(ancestor.getClass())) {
				return clazz.cast(ancestor);
			}
		}
		return null;
	}

	public static String getUniqueId(EObject object) {
		String id;
		if (object instanceof CDOObject) {
			CDOObject cdoObject = (CDOObject) object;
			return buildStringId(cdoObject.cdoID());
		} else {
			return null;
		}
	}

	public static String buildStringId(CDOID id) {
		StringBuilder builder = new StringBuilder();
		CDOIDUtil.write(builder, id);
		String idAsString = builder.toString();
		return idAsString;
	}

	public static <T> T getLastAncestorOfType(EObject object, Class<T> clazz) {
		EObject ancestor = object;
		T currentAncestor = null;
		if (clazz.isAssignableFrom(object.getClass())) {
			currentAncestor = clazz.cast(object);
		}
		while (ancestor != null) {
			ancestor = ancestor.eContainer();
			if (ancestor != null) {
				if (clazz.isAssignableFrom(ancestor.getClass())) {
					currentAncestor = clazz.cast(ancestor);
				}
			}
		}
		return currentAncestor;
	}

	public static String getProjectId(EObject target) {
		Folder projectFolder = getLastAncestorOfType(target, Folder.class);
		if (projectFolder != null && projectFolder.cdoState() != CDOState.TRANSIENT) {
			return projectFolder.getId();
		} else {
			return null;
		}
	}

	public static boolean isProject(EObject target) {
		Folder projectFolder = getLastAncestorOfType(target, Folder.class);
		return target == projectFolder;
	}

	public static List<EObject> getChildren(Object target) throws SpecmateException {
		if (target instanceof Resource) {
			return ((Resource) target).getContents();

		} else if (target instanceof EObject) {
			return ((EObject) target).eContents();
		} else {
			throw new SpecmateInternalException(ErrorCode.INTERNAL_PROBLEM, "Object is no resource and no EObject.");
		}
	}

	public static List<TestStep> getStepsSorted(TestProcedure testProcedure) {
		List<TestStep> steps = SpecmateEcoreUtil.pickInstancesOf(testProcedure.getContents(), TestStep.class);
		steps.sort((s1, s2) -> Integer.compare(s1.getPosition(), s2.getPosition()));
		return steps;
	}

}

@SuppressWarnings("serial")
class ShallowCopier extends EcoreUtil.Copier {
	@Override
	protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
	}
}
