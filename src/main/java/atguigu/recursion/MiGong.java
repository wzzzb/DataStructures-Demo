package atguigu.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组8行7列当做地图
        int[][] map = new int[8][7];

        //使用1当做墙体，四周做成墙体
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //在地图内部设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //map[1][2] = 1;
        map[2][2] = 1;

        //展示地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //展示小球走过的地图
        System.out.println("----------------------小球走过------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //规则
    //1.map表示地图
    //2.i,j表示小球的初始位置（1,1）
    //3.如果小球能到map[6][5]位置（右下角），则说明通路找到
    //4.约定：当map[i][j]=0表示该点没有走过，1：，表示墙不能走，2：表示通路可以走，3：表示该位置已经走过，但是走不通
    //5.在走迷宫时，需要确定一个行进的策略：下->右->上->左（也可以用其他策略），如果该点走不通，再回溯
    /*
        map 表示地图
        i,j表示小球的初始位置
        boolean :如果找到通路就返回true,否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略下->右->上->左（可以用其他策略）
                map[i][j] = 2;  //假定该点是可以走通的
             //只有将setWay(map, i + 1, j)当成条件语句，才能在走不通时回溯并换一个方向
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明从（i,j）点是走不通的，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0 可能是1,2（该点也走过了，递归找到2说明递归死循环了）,3
                return false;
            }
        }
    }
}
