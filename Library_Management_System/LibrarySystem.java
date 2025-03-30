import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private List<LibraryMember> members;
    private List<LibraryItem> items;

    public LibrarySystem() {
        members = new ArrayList<>();
        items = new ArrayList<>();
    }

    // Üye kaydı yapma
    public void registerMember(LibraryMember member) throws DuplicateMemberException {
        for (LibraryMember m : members) {
            if (m.getMemberId() == member.getMemberId()) {
                throw new DuplicateMemberException("Member with ID " + member.getMemberId() + " already exists.");
            }
        }
        members.add(member);
    }

    // Yeni öğe ekleme
    public void addNewItem(LibraryItem item) throws DuplicateItemException {
        for (LibraryItem i : items) {
            if (i.getItemId().equals(item.getItemId())) {
                throw new DuplicateItemException("Item with ID " + item.getItemId() + " already exists.");
            }
        }
        items.add(item);
    }

    // Üye arama
    public LibraryMember findMemberById(int memberId) throws UserNotFoundException {
        for (LibraryMember member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new UserNotFoundException("Member with ID " + memberId + " not found.");
    }

    // Öğe arama
    public LibraryItem findItemById(String itemId) throws ItemNotFoundException {
        for (LibraryItem item : items) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        throw new ItemNotFoundException("Item with ID " + itemId + " not found.");
    }

    // Öğe ödünç alma
    public void borrowItem(int memberId, String itemId) throws UserNotFoundException, ItemNotFoundException, OverLimitException, ItemAlreadyBorrowedException {
        LibraryMember member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);

        member.borrowItem(); // Borrow limit kontrolü burada yapılır
        item.borrowItem(); // Öğe zaten ödünç alınmışsa exception fırlatır
        System.out.println(member.getName() + " borrowed " + item.getTitle());
    }

    // Öğe iade etme
    public void returnItem(int memberId, String itemId) throws UserNotFoundException, ItemNotFoundException {
        LibraryMember member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);

        member.returnItem();
        item.returnItem();
        System.out.println(member.getName() + " returned " + item.getTitle());
    }

    // Tüm üyeleri yazdır
    public void printAllMembers() {
        for (LibraryMember member : members) {
            System.out.println(member);
        }
    }

    // Tüm öğeleri yazdır
    public void printAllItems() {
        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }
}


class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

class DuplicateMemberException extends Exception {
    public DuplicateMemberException(String message) {
        super(message);
    }
}

class DuplicateItemException extends Exception {
    public DuplicateItemException(String message) {
        super(message);
    }
}

class OverLimitException extends RuntimeException {
    public OverLimitException(String message) {
        super(message);
    }
}

class InvalidMemberNameException extends RuntimeException {
    public InvalidMemberNameException(String message) {
        super(message);
    }
}

class InvalidItemTitleException extends RuntimeException {
    public InvalidItemTitleException(String message) {
        super(message);
    }
}

class ItemAlreadyBorrowedException extends Exception {
    public ItemAlreadyBorrowedException(String message) {
        super(message);
    }
}


class LibraryMember {
    private String name;
    private int memberId;
    private int borrowedCount;
    private int borrowLimit = 5;

    public LibraryMember(String name, int memberId) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMemberNameException("Member name cannot be empty or null.");
        }
        this.name = name;
        this.memberId = memberId;
        this.borrowedCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public void borrowItem() {
        if (borrowedCount >= borrowLimit) {
            throw new OverLimitException("Member has reached the borrow limit.");
        }
        borrowedCount++;
    }

    public void returnItem() {
        if (borrowedCount > 0) {
            borrowedCount--;
        }
    }
    
    @Override
    public String toString() {
        return "LibraryMember{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                ", borrowedCount=" + borrowedCount +
                ", borrowLimit=" + borrowLimit +
                '}';
    }
}

 class LibraryItem {
    private String title;
    private String itemId;
    private boolean isBorrowed;

    public LibraryItem(String title, String itemId) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidItemTitleException("Item title cannot be empty or null.");
        }
        this.title = title;
        this.itemId = itemId;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getItemId() {
        return itemId;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Ödünç alma işlemi sırasında öğenin zaten ödünçte olup olmadığını kontrol ediyoruz
    public void borrowItem() throws ItemAlreadyBorrowedException {
        if (isBorrowed) {
            throw new ItemAlreadyBorrowedException("Item is already borrowed.");
        }
        isBorrowed = true;
    }

    public void returnItem() {
        if (!isBorrowed) {
            throw new IllegalStateException("Item is not borrowed.");
        }
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + title + '\'' +
                ", itemId='" + itemId + '\'' +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
