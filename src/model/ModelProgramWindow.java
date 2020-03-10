package model;


/**
 * Class representing the Program Window where the blocks will be set.
 */
class ModelProgramWindow extends ModelWindow{

    private int width;
    private int height;

    
    public ModelProgramWindow(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}