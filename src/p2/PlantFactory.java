package p2;

public class PlantFactory {
		private static Plant[] availablePlants = {
			
		};
		
		public static Plant getPlant(String plantName){
			Plant p = null;
			for (Plant item : availablePlants) {
				if(plantName.equals(item.getName()))
					p = item;
			}
			return p;
		}
		
		// TODO cost y harm constantes, atributos, métodos, what.
		public static String listOfAvilablePlants() {
			String sol = "";
			for (Plant item : availablePlants) {
				sol += item.getName() + ": Cost: " + Integer.toString(item.cost) + " suncoins Harm: " + Integer.toString(item.harm) + "\n";
			}
			return sol;
		}
}
