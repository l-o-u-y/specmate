package com.specmate.modelgeneration;

import com.specmate.common.exception.SpecmateException;
import com.specmate.model.requirements.RGModel;

public interface IRGFromRequirementGenerator {
	public abstract RGModel createModel(RGModel model, String text) throws SpecmateException;
}
