public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        try {
            // Üyeleri oluştur
            LibraryMember member1 = new LibraryMember("John Doe", 1);
            LibraryMember member2 = new LibraryMember("Jane Smith", 2);
            library.registerMember(member1);
            library.registerMember(member2);
        } catch (DuplicateMemberException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            // Öğe oluştur
            LibraryItem book1 = new LibraryItem("The Catcher in the Rye", "B1");
            LibraryItem book2 = new LibraryItem("1984", "B2");
            library.addNewItem(book1);
            library.addNewItem(book2);
        } catch (DuplicateItemException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            // Ödünç alma işlemi
            library.borrowItem(1, "B1");
            library.borrowItem(2, "B2");
            // Aynı kitabı tekrar ödünç almaya çalışmak (hata fırlatacak)
            library.borrowItem(1, "B1");
        } catch (UserNotFoundException | ItemNotFoundException | OverLimitException | ItemAlreadyBorrowedException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            // İade işlemi
            library.returnItem(1, "B1");
            library.returnItem(2, "B2");
        } catch (UserNotFoundException | ItemNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Tüm üyeleri ve öğeleri yazdır
        System.out.println("\nAll Members:");
        library.printAllMembers();

        System.out.println("\nAll Items:");
        library.printAllItems();
    }
}
