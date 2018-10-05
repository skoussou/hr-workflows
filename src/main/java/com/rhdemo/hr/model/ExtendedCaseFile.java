package com.rhdemo.hr.model;

import java.io.Serializable;

@org.kie.api.definition.type.Label("ExtendedCaseFile")
public class ExtendedCaseFile extends org.kie.server.api.model.cases.CaseInstance implements Serializable {

  private static final long serialVersionUID = 1L;
  

  private String caseOwner;

  public String getCaseOwner() {
    return caseOwner;
  }
  public void setCaseOwner(String caseOwner) {
      this.caseOwner = caseOwner;
  }
  
}
