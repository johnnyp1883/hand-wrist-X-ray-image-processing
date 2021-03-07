/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

/**
 *
 * @author Makoto
 */
public class Reflection {
     private int tempImage[][];
     
 
 
  public Reflection(int[][] inputImage,int tempH,int tempW,int sizeMid)
  {
    tempImage=new int[tempH][tempW];
    for (int i = sizeMid; i < tempH-sizeMid; i++)
      for (int j = sizeMid; j < tempW-sizeMid; j++)
         //將輸入的圖的pixel值輸入tempImage的正中央，留下四周sizeMid寬的空白
          tempImage[i][j] = inputImage[i-sizeMid][j-sizeMid];

    //i方向
    for (int i = 0; i < tempH; i++)
      for (int j = sizeMid; j < tempW - sizeMid; j++) {
        if (i < sizeMid)
          tempImage[i][j] = tempImage[sizeMid - i + sizeMid - 1][j];
        //若pixel屬於原圖的下方，從原圖的相對位置做鏡射
        else if (i >= tempH - sizeMid)    
          tempImage[i][j] = tempImage[tempH - sizeMid - (i - (tempH - sizeMid - 1))][j];
        //若pixel高於原圖，從原圖相對位置做鏡射
      }

    //j方向
    for (int i = 0; i < tempH; i++)
      for (int j = 0; j < tempW; j++) {
        if (j < sizeMid)
          tempImage[i][j] = tempImage[i][(sizeMid-1) - j + sizeMid ];
        //若pixel在原圖左方，從原圖相對位置做鏡射
        else if (j >= tempW - sizeMid)
          tempImage[i][j] = tempImage[i][tempW - sizeMid -
              (j - (tempW - sizeMid - 1))];
        //若pixel在原圖右方，從原圖相對位置做鏡射
      }
  }

  public int[][] getImage()
  {
    return tempImage;
  }
}
