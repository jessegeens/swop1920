package model;

import org.w3c.dom.events.MouseEvent;

import model.blocks.*;
import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette extends ModelWindow{

    

    private final Location turnLeftLocation = new Location(20, 20);
    private final Location turnRightLocation = new Location(160, 20);
    private final Location forwardLocation = new Location(300, 20);
    private final Location notLocation = new Location(440, 20);
    private final Location wallInFrontLocation = new Location(20, 160);
    private final Location whileLocation = new Location(160, 160);
    private final Location ifLocation = new Location(300, 160);

    private ModelMoveBlock turnLeftBlock;
    private ModelMoveBlock turnRightBlock;
    private ModelMoveBlock forwardBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallInFrontBlock;
    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;


    public ModelPalette(int width, int height){
        super(width,height);
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), new Blocktype(Blocktype.TURNLEFT)));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(), new Blocktype(Blocktype.TURNRIGHT)));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
        this.setNotBlock(new ModelNotBlock(this.getNotLocation(),new Blocktype(Blocktype.NOT)));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation(),new Blocktype(Blocktype.WALLINFRONT)));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),new Blocktype(Blocktype.WHILE)));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),new Blocktype(Blocktype.IF)));
    }

    /**
     * Method to add a block to the program window.
     * @param blk the block to be added from the palette into the program window. 
     * @param limitReached true than it won't be possible to place a block of some specific type into the program window.
     */

    //TODO limitreached is better of being a global var in modelpalette than a method parameter
    //or maybe have a static int called maxamountofelements somewhere?
    public void blockToProgramWindow(ModelBlock blk, Boolean limitReached){
        if (limitReached){
            setTurnLeftBlock(null);
            setTurnRightBlock(null);
            setForwardBlock(null);
            setNotBlock(null);
            setWallInFrontBlock(null);
            setWhileBlock(null);
            setIfBlock(null);
        }
        else{
            switch(blk.getBlockType().getType()){
                case Blocktype.IF:
                    this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),new Blocktype(Blocktype.IF)));
                case Blocktype.WHILE:
                    this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),new Blocktype(Blocktype.WHILE)));
                case Blocktype.MOVEFORWARD:
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
                case Blocktype.TURNLEFT:
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), new Blocktype(Blocktype.TURNLEFT)));
                case Blocktype.TURNRIGHT:
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(), new Blocktype(Blocktype.TURNRIGHT)));
                case Blocktype.WALLINFRONT:
                    this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation(),new Blocktype(Blocktype.WALLINFRONT)));
                case Blocktype.NOT:
                    this.setNotBlock(new ModelNotBlock(this.getNotLocation(),new Blocktype(Blocktype.NOT)));
                default:
            }
        }    
    }

    /**
     * 
     * @return a turn left block.
     */
    public ModelMoveBlock getTurnLeftBlock() {
        return this.turnLeftBlock;
    }

    /**
     * 
     * @param turnLeftBlock sets the turn left block.
     */
    public void setTurnLeftBlock(ModelMoveBlock turnLeftBlock) {
        this.turnLeftBlock = turnLeftBlock;
    }

    /**
     * 
     * @return a turn right block.
     */
    public ModelMoveBlock getTurnRightBlock() {
        return this.turnRightBlock;
    }

    /**
     * 
     * @param turnRightBlock sets the turn right block.
     */
    public void setTurnRightBlock(ModelMoveBlock turnRightBlock) {
        this.turnRightBlock = turnRightBlock;
    }

    /**
     * 
     * @return the forward block.
     */
    public ModelMoveBlock getForwardBlock() {
        return this.forwardBlock;
    }

    /**
     * 
     * @param forwardBlock sets the forward block.
     */
    public void setForwardBlock(ModelMoveBlock forwardBlock) {
        this.forwardBlock = forwardBlock;
    }

    /**
     * 
     * @return the not block.
     */
    public ModelNotBlock getNotBlock() {
        return this.notBlock;
    }

    /**
     * 
     * @param notBlock sets the not block.
     */
    public void setNotBlock(ModelNotBlock notBlock) {
        this.notBlock = notBlock;
    }

    /**
     * 
     * @return the 'wall in front' block.
     */
    public ModelWallInFrontBlock getWallInFrontBlock() {
        return this.wallInFrontBlock;
    }

    /**
     * 
     * @param wallInFrontBlock sets the 'wall in front' block.
     */
    public void setWallInFrontBlock(ModelWallInFrontBlock wallInFrontBlock) {
        this.wallInFrontBlock = wallInFrontBlock;
    }

    /**
     * 
     * @return the while block.
     */
    public ModelWhileIfBlock getWhileBlock() {
        return this.whileBlock;
    }

    /**
     * 
     * @param whileBlock sets the while block.
     */
    public void setWhileBlock(ModelWhileIfBlock whileBlock) {
        this.whileBlock = whileBlock;
    }

    /**
     * 
     * @return the if block.
     */
    public ModelWhileIfBlock getIfBlock() {
        return this.ifBlock;
    }

    /**
     * 
     * @param ifBlock sets the if block.
     */
    public void setIfBlock(ModelWhileIfBlock ifBlock) {
        this.ifBlock = ifBlock;
    }

    /**
     * 
     * @return the location of the 'turn left' block.
     */
    public Location getTurnLeftLocation(){
        return this.turnLeftLocation;
    }

    /**
     * 
     * @return the location of the 'turn right' block.
     */
    public Location getTurnRightLocation(){
        return this.turnRightLocation;
    }

    /**
     * 
     * @return the location of the 'forward' block.
     */
    public Location getForwardLocation(){
        return this.forwardLocation;
    }

    /**
     * 
     * @return the location of the not block location.
     */
    public Location getNotLocation(){
        return this.notLocation;
    }

    /**
     * 
     * @return the location of the 'wall in front' block.
     */
    public Location getWallInFrontLocation(){
        return this.wallInFrontLocation;
    }

    /**
     * 
     * @return the location of the if block.
     */
    public Location getIfLocation(){
        return this.ifLocation;
    }

    /**
     * 
     * @return the location of the while block.
     */
    public Location getWhileLocation(){
        return this.whileLocation;
    }

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){

        //MOUSE_PRESSED where you start holding the button down 501
        //MOUSE_RELEASED where you release the button      502  
        //MOUSE_CLICKED => press + release (comes after released + pressed) only comes if no dragging happended 500
        //MOUSE_DRAGGED => Holding down, gets triggerd after each small move 506
        //interesting to know: there is no difference detected between left and right button in the current handlemouseevent function

        //TODO cannot acces static MouseEvent.MOUSE_PRESSED etc

        //mouse pressed: make a new block if in bounds of a specific block
        if(id == 501){
            ModelBlock selected = null;
            if(this.turnLeftBlock.inBounds(eventLocation)){
                //super.addBlock(this.turnLeftBlock);
                //TODO between these steps you need to reassign the old block
                this.blockToProgramWindow(this.turnLeftBlock, false);
            }
            else if(this.turnRightBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.turnRightBlock, false);
            }
            else if(this.forwardBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.forwardBlock, false);
            }
            else if(this.notBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.notBlock, false);
            }
            else if(this.wallInFrontBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.wallInFrontBlock, false);
            }
            else if(this.whileBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.whileBlock, false);
            }
            else if(this.ifBlock.inBounds(eventLocation)){
                this.blockToProgramWindow(this.ifBlock, false);
            }
        }

        //MOUSE RELEASED, delete the currently held item (if there is one)
        else if(id == 502){
            

        }

        //MOUSE MOVED, if there is a currently held block, move it
        else if(id == 506){
            
            
            
            //update pos of activelement
        }
        

        //if mouse down on 
        //if mouse move
        //if mouse up, remove active element super.deleteelement





        /*

         private ModelMoveBlock turnLeftBlock;
    private ModelMoveBlock turnRightBlock;
    private ModelMoveBlock forwardBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallInFrontBlock;
    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;
    */

    }

    
}