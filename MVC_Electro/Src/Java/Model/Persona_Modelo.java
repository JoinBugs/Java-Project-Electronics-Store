public class Persona_Modelo
{

private String name, dir, tel, face; 	
private Boolean married;

	public Persona_Modelo() {}

	public Persona_Modelo(String nomb, String dir, String tel, String correo, Boolean marr)
	{ 
		this.dir = dir;
		this.tel = tel;
		name = nomb;
		face = correo;
		married = marr;	
	}
//---------------------------------- Setters ----------------------------------------
		public void setName(String nomb)		{ name = nomb; }
	
		public void setDireccion(String dire)	{ dir = dire; }
	
		public void setTelefono(String teln)	{ tel = teln; }

		public void setFaceBook(String corr)	{ face = corr; }

		public void setMarried(Boolean marr)	{ married = marr; }
		
//---------------------------------- Getters -----------------------------------------
		
		public String getName() 	{ return name; }
		
		public String getDireccion(){ return dir; }
		
		public String getTelefono()	{ return tel; }
		
		public String getFaceBook()	{ return face; }
		
		public Boolean isMarried()	{ return married; }
}