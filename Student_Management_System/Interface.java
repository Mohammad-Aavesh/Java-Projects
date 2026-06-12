public class  Interface{
	static int choice = 0;
    public static void main(String[] args) throws Exception {

		do{
			System.out.println("---------------------------------");
			System.out.println("| Enter 1 to go to Admin Panel   |");
			System.out.println("| Enter 2 to go to Student Panel |");
			System.out.println("| Enter 3 to Exit the Program    |");
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
