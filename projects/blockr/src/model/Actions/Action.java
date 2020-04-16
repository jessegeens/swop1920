package model.Actions;


/**
 * Action interface, follows the command pattern
 *
 * @author bert_dvl
 */
public interface Action {

    public void execute();
    public void undo();
    public void redo();





}
