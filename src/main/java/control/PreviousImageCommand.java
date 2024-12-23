package control;


public class PreviousImageCommand implements Command{
    Presenter presenter;

    public PreviousImageCommand(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.image().prev());
    }
}
