package control;


public class NextImageCommand implements Command{
    Presenter presenter;

    public NextImageCommand(Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void execute() {
        presenter.show(presenter.image().next());
    }
}
