package com.specmate.migration.internal.services;

import java.sql.Connection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.specmate.common.exception.SpecmateException;
import com.specmate.dbprovider.api.IDBProvider;
import com.specmate.dbprovider.api.migration.IAttributeToSQLMapper;
import com.specmate.dbprovider.api.migration.IObjectToSQLMapper;
import com.specmate.migration.api.IMigrator;
import com.specmate.model.base.BasePackage;

// TODO this migrator doesnt work yet
@Component(property = "sourceVersion=20200309", service = IMigrator.class)
public class Migrator20200309 implements IMigrator {

	private IDBProvider dbProvider;

	@Override
	public String getSourceVersion() {
		return "20200309";
	}

	@Override
	public String getTargetVersion() {
		return "20200612";
	}

	@Override
	public void migrate(Connection connection) throws SpecmateException {
		migrateObject("model/requirements", "RGModel");
		migrateObject("model/requirements", "RGNode");
		migrateObject("model/requirements", "RGConnection");
		


		IAttributeToSQLMapper aMapper = this.dbProvider.getAttributeToSQLMapper("model/requirements", getSourceVersion(),
				getTargetVersion());
		aMapper.migrateNewStringAttribute("RGModel", "name", "");
		aMapper.migrateNewStringAttribute("RGModel", "description", "");
		aMapper.migrateNewStringAttribute("RGModel", "modelRequirements", "");

		aMapper.migrateNewStringAttribute("RGNode", "name", "");
		aMapper.migrateNewStringAttribute("RGNode", "description", "");
		aMapper.migrateNewStringAttribute("RGNode", "id", "");
		aMapper.migrateNewStringAttribute("RGNode", "url", "");
		aMapper.migrateNewBooleanAttribute("RGNode", "recycled", false);
		aMapper.migrateNewBooleanAttribute("RGNode", "hasRecycledChildren", false);
		aMapper.migrateNewStringAttribute("RGNode", "type", "Component");
		aMapper.migrateNewDoubleAttribute("RGNode", "x", 0.0);
		aMapper.migrateNewDoubleAttribute("RGNode", "y", 0.0);

		aMapper.migrateNewStringAttribute("RGNode", "name", "");
		aMapper.migrateNewStringAttribute("RGNode", "description", "");
		aMapper.migrateNewStringAttribute("RGNode", "id", "");
		aMapper.migrateNewStringAttribute("RGNode", "url", "");
		aMapper.migrateNewBooleanAttribute("RGNode", "recycled", false);
		aMapper.migrateNewBooleanAttribute("RGNode", "hasRecycledChildren", false);
		aMapper.migrateNewBooleanAttribute("RGConnection", "negate", false);
		aMapper.migrateNewStringAttribute("RGConnection", "type", "Inheritance");
	}

	private void migrateObject(String packageName, String objectName) throws SpecmateException {
		IObjectToSQLMapper oMapper = this.dbProvider.getObjectToSQLMapper(packageName, getSourceVersion(),
				getTargetVersion());
		oMapper.newObject(objectName);
		
	}

	@Reference
	public void setDBProvider(IDBProvider dbProvider) {
		this.dbProvider = dbProvider;
	}

}