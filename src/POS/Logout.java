package POS;

import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.*;

public class Logout extends ActionSupport {
  public String logout() throws Exception { 
  Map session = ActionContext.getContext().getSession();
  session.remove("logged-in"); 
  return "success";
  }
}