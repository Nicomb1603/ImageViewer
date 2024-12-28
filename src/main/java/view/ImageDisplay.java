package view;



public interface ImageDisplay<T> {
    //void show(T image);
    void paintImage(T image, int x);

    void onReleased(Offset offset);

    void onDragged(Offset offset);

  interface Offset {
        static void Null(int offset) {}
        void handle(int offset);
    }
    void clear();

    int width();


}
