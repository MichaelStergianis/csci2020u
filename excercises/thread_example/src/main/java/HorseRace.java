public final class HorseRace {
	public HorseRace(){
		Thread[] horses = new Thread[5];
		horses[0] = new Thread(new Horse("Seabiscuit"));
		horses[1] = new Thread(new Horse("Hidalgo"));
		horses[2] = new Thread(new Horse("Man o' War"));
		horses[3] = new Thread(new Horse("Secretariat"));
		horses[4] = new Thread(new Horse("War Admiral"));
		
		System.out.println("Ready...\nSet...\nGo...");
		for (Thread i: horses){
			i.start();
		}
	}
	public static void main(String[] args){
		HorseRace hr = new HorseRace();
	}

	class Horse implements Runnable {
		final int ITERATIONS = 1000000000;
		private String name;
		public String getName(){
			return this.name;
		}
		public Horse(String name) {
			this.name = name;
		}
		public void run() {
			for (int i = 0; i < ITERATIONS; i++){
				if ( (i % 100000000) == 0 ){
					System.out.println(getName() + " is at " + (i / 10000000) + "m");
				}
			}
			System.out.println(getName() + " has finished.");
		}
	}
}

