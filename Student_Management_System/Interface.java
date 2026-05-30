public class  Interface{
	static int choice = 0;
    public static void main(String[] args) throws Exception {

		do{
			System.out.println("---------------------------------");
			System.out.println("| To Enter Admin Panel use    1 |");
			System.out.println("| To Enter Student Panel use  2 |");
			System.out.println("| To Exit Program use         3 |");
			System.out.println("---------------------------------");
			System.out.print("Enter the choice :");

				choice = Validation.checker(0);

				switch (choice) {
					case 1:
						Validation.Enter_Admin();
					break;

					case 2:
						Validation.Enter_Student();
					break;

					case 3:
						System.out.println("Thank you for using Student Management System.");
					break;
		
					default:
						System.out.println("Enter the right choice.");
					break;
				}
				System.out.println();
		}while(choice != 3);
    }
	
}