// MyString.java
public class MyString {

    private String data;

    public MyString(String data) {
        this.data = data;
    }

    // Checks if the current MyString is a subset of another MyString
    public boolean subsetOf(MyString other) {
        String temp = other.data;
        for (char c : this.data.toCharArray()) {
            int index = temp.indexOf(c);
            if (index == -1) {
                return false;
            }
            temp = temp.substring(0, index) + temp.substring(index + 1);
        }
        return true;
    }

    // Removes the characters in another MyString from the current MyString
    public void remove(MyString other) {
        StringBuilder temp = new StringBuilder(this.data);
        for (char c : other.data.toCharArray()) {
            int index = temp.indexOf(String.valueOf(c));
            if (index != -1) {
                temp.deleteCharAt(index);
            }
        }
        this.data = temp.toString();
    }

    // Returns the string representation of the MyString
    public String toString() {
        return this.data;
    }
}
