import java.util.ArrayList;
import java.util.List;

/**
 * 笔记管理器类
 */
public class NoteManager {
    private List<Note> notes;

    public NoteManager() {
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(notes);
    }

    public List<Note> searchNotes(String keyword) {
        List<Note> results = new ArrayList<>();
        for (Note note : notes) {
            if (note.getTitle().contains(keyword) || note.getContent().contains(keyword)) {
                results.add(note);
            }
        }
        return results;
    }

    public void deleteNote(Note note) {
        notes.remove(note);
    }

    public void updateNote(Note oldNote, Note newNote) {
        int index = notes.indexOf(oldNote);
        if (index != -1) {
            notes.set(index, newNote);
        }
    }

    public Note getNoteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }
}