package control;


public class PreviousImageCommand implements Command{
    Presenter presenter;

    private PreviousImageCommand(Presenter presenter) {
        this.presenter = presenter;
    }


    public static Command createCommand(Presenter presenter) {
        return new PreviousImageCommand(presenter);
    }

    @Override
    public void execute() {
        presenter.show(presenter.image().prev());
    }
}
