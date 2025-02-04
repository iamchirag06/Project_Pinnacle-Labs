import java.util.*;

class Note {
    private String title;
    private String content;
    private Date createdDate;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class NoteManager {
    private List<Note> notes = new ArrayList<>();

    public void createNote(String title, String content) {
        notes.add(new Note(title, content));
        System.out.println("Note created successfully.");
    }

    public void listNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
            return;
        }
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println((i + 1) + ". " + note.getTitle() + " (Created: " + note.getCreatedDate() + ")");
        }
    }

    public Note getNoteByIndex(int index) {
        if (index < 1 || index > notes.size()) {
            System.out.println("Invalid note selection.");
            return null;
        }
        return notes.get(index - 1);
    }

    public void editNoteContent(int index, String newContent) {
        Note note = getNoteByIndex(index);
        if (note != null) {
            note.setContent(newContent);
            System.out.println("Note updated successfully.");
        }
    }

    public void deleteNoteByIndex(int index) {
        if (index < 1 || index > notes.size()) {
            System.out.println("Invalid note selection.");
            return;
        }
        notes.remove(index - 1);
        System.out.println("Note deleted successfully.");
    }
}

public class NoteTakingApp {
    private static NoteManager noteManager = new NoteManager();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("\nNote Taking App:");
                    System.out.println("1. Create Note");
                    System.out.println("2. List Notes");
                    System.out.println("3. View Note");
                    System.out.println("4. Edit Note");
                    System.out.println("5. Delete Note");
                    System.out.println("6. Exit");
                    System.out.print("Choose an option: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            System.out.print("Enter note title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter note content: ");
                            String content = scanner.nextLine();
                            noteManager.createNote(title, content);
                            break;
                        case 2:
                            noteManager.listNotes();
                            break;
                        case 3:
                            System.out.print("Enter note number to view: ");
                            int viewIndex = Integer.parseInt(scanner.nextLine());
                            Note note = noteManager.getNoteByIndex(viewIndex);
                            if (note != null) {
                                System.out.println("\nTitle: " + note.getTitle());
                                System.out.println("Content: " + note.getContent());
                                System.out.println("Created: " + note.getCreatedDate());
                            }
                            break;
                        case 4:
                            System.out.print("Enter note number to edit: ");
                            int editIndex = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter new content: ");
                            String newContent = scanner.nextLine();
                            noteManager.editNoteContent(editIndex, newContent);
                            break;
                        case 5:
                            System.out.print("Enter note number to delete: ");
                            int deleteIndex = Integer.parseInt(scanner.nextLine());
                            noteManager.deleteNoteByIndex(deleteIndex);
                            break;
                        case 6:
                            System.out.println("Exiting application. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        }
    }
}
