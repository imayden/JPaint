package view.interfaces;

// ydeng24@depaul.edu

public interface IDialogChoice<T> {
    String getDialogTitle();

    String getDialogText();

    T[] getDialogOptions();

    T getCurrentSelection();
}
