/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;


public class NegativeImg {
    private int[][] result;
    NegativeImg(int[][] img,int height,int width ){
    result=new int[height][width];    
    for(int i=0;i<height;i++)
        for(int j=0;j<width;j++)
            result[i][j]=255-img[i][j];
    
    
    }
    public int[][] getImage(){
    return result;
    }
}
