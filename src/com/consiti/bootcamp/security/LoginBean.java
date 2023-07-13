package com.consiti.bootcamp.security;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

@javax.faces.bean.ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

//Logger log = Logger.getLogger(LoginBean.class);

	private String envName;

	@PostConstruct
	public void beforeLoad() {
		this.envName = "DEV";
	}

	public void login() {
		try {
			System.out.println("Entro a login");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest) context.getRequest();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
			dispatcher.forward((ServletRequest) request, (ServletResponse) response);
			System.out.println("Response: " + response.getStatus());
		} catch (Exception e) {
//this.log.error(e);
			e.printStackTrace();
			mensaje(e.getMessage());
		}
	}

	public void mensaje(String respuesta) {
		FacesMessage mensaje = new FacesMessage(respuesta);
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public String getEnvName() {
		return this.envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

}