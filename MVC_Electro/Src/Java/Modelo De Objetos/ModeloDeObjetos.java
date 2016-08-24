public class ModeloDeObjetos
{
	public static void main(String[] args)
	{
		Persona_Modelo persona = new Persona_Modelo();
		
		persona.setName("Andres");
		persona.setDireccion("Francisco I Madero");
		persona.setTelefono("445 67 89 54");
		persona.setFaceBook("andres@hotmail.com");
		persona.setMarried(false);
		
		System.out.print(persona.getName());
		System.out.print(persona.getDireccion());
		System.out.print(persona.getTelefono());
		System.out.print(persona.getFaceBook());
		System.out.print(persona.isMarried());
		
	}
}