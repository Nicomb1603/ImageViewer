package control;


public class NextImageCommand implements Command{
    Presenter presenter;

    private NextImageCommand(Presenter presenter) {
        this.presenter = presenter;
    }


    public static Command createCommand(Presenter presenter) {
        return new NextImageCommand(presenter);
    }

    @Override
    public void execute() {
        presenter.show(presenter.image().next());
    }
}
