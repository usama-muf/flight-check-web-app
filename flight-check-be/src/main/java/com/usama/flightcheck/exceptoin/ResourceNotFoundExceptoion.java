package com.usama.flightcheck.exceptoin;

public class ResourceNotFoundExceptoion extends RuntimeException{
	
	private String attributeName;
	private String attributeValue;
	private String resourceName;
	
	public ResourceNotFoundExceptoion(String attributeName, String attributeValue, String resourceName) {
		super(String.format("Resource with %s %s not found in %s", attributeName, attributeValue, resourceName));
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.resourceName = resourceName;
		}

}
