package atguigu.sparsearray;

import java.io.*;

/**
 *
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;


        //遍历二维数组。将非0的值，存放到稀疏数组中
        int count = 0;//用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为如下形式");
        for (int i = 0; i < sparseArray.length; i++) {
            //\t为空一格 ,  \n为换行 ,   %d代指一个数据
            System.out.printf("%d\t%d\t%d\t\n",
                    sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        //将二维数组写入文件中
        File file = new File("d:\\code\\SparseArray.txt");  //存放数组数据的文件
        FileWriter out = new FileWriter(file);  //文件写入流

        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for (int i = 0; i < sparseArray.length; i++) {
            out.write(sparseArray[i][0] + "\t");
            out.write(sparseArray[i][1] + "\t");
            out.write(sparseArray[i][2] + "\t");

            out.write("\r\n");
        }
        out.close();

        //从文件中读取二维数组

        //3.将稀疏数组恢复成原始的二维数组
        //1)先读取稀疏数组第一行，创建一个新的二维数组
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        //2)将稀疏数组的非零项归还给二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}

