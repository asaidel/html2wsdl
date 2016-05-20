package html2wsdl.parameters;

public class InputParameters
{
  private String objeto;
  private String entidadDiscreta;
  private String operacion;
  private String version;
  private String negocio;
  private String localizacion;
  private String capa;
  private String versionXSD;
  private String backend;
  
  public InputParameters(String objeto, String entidadDiscreta, String operacion, String version, String negocio, String localizacion, String capa, String versionXSD)
  {
    this.objeto = objeto;
    this.entidadDiscreta = entidadDiscreta;
    this.operacion = operacion;
    this.version = version;
    this.negocio = negocio;
    this.localizacion = localizacion;
    this.versionXSD = versionXSD;
  }
  
  public String getEntidadDiscreta()
  {
    return this.entidadDiscreta;
  }
  
  public void setEntidadDiscreta(String entidadDiscreta)
  {
    this.entidadDiscreta = entidadDiscreta;
  }
  
  public String getOperacion()
  {
    return this.operacion;
  }
  
  public void setOperacion(String operacion)
  {
    this.operacion = operacion;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getNegocio()
  {
    return this.negocio;
  }
  
  public void setNegocio(String negocio)
  {
    this.negocio = negocio;
  }
  
  public String getLocalizacion()
  {
    return this.localizacion;
  }
  
  public void setLocalizacion(String localizacion)
  {
    this.localizacion = localizacion;
  }
  
  public String getVersionXSD()
  {
    return this.versionXSD;
  }
  
  public void setVersionXSD(String versionXSD)
  {
    this.versionXSD = versionXSD;
  }
  
  public String getObjeto()
  {
    return this.objeto;
  }
  
  public void setObjeto(String objeto)
  {
    this.objeto = objeto;
  }
  
  public String getCapa()
  {
    return this.capa;
  }
  
  public void setCapa(String capa)
  {
    this.capa = capa;
  }
  
  public String getBackend()
  {
    return this.backend;
  }
  
  public void setBackend(String backend)
  {
    this.backend = backend;
  }
}
