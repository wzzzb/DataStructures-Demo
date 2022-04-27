package atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//要完成将一个中缀表达式转后缀表达式的功能
//说明：
//1.1+((2+3)×4)-5 => 1 2 3 + 4 × + 5 –
//2.因为直接对一个字符串进行操作不方便，因此先将字符串"1+((2+3)×4)-5" => 中缀表达式对应的List
//即"1+((2+3)×4)-5" => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
//3.将得到的中缀表达式对应的List转成后缀表达式对应的List
// 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,–]
public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";  //注意表达式的写法
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); //ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]

        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List=" + suffixExpressionList); //List=[1, 2, 3, +, 4, *, +, 5, -]

        System.out.printf("expression= %d\n", calculate(suffixExpressionList));


        /*//先定义一个逆波兰表达式
        // (30+4)×5-6 =>  30 4 + 5 × 6 -
        //4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //测试
        //为了方便，逆波兰表达式 数字和符号使用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将"3 4 + 5 × 6 -"放到ArrayList中
        //2.将ArrayList 传给一个方法 ，遍历 ArrayList 配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println("rnpList=" + list);

        int res = calculate(list);
        System.out.println("计算的结果是=" + res);*/

    }

    // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,×,+,5,–]
    //方法：将得到的中缀表达式对应的List转成后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();  //符号栈
        //说明，因为s2栈，在整个转换过程中没有pop动作，后续还需要逆序输出，比较麻烦
        //因此我们不使用栈了，用List<String> s2来进行存储
        List<String> s2 = new ArrayList<>();  //存储中间结果List s2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  //!!!将这个"("弹出s1栈，消除一对小括号
            } else {
                //当item的优先级小于等于栈顶运算符的优先级
                //将s1栈顶的运算符弹出并加入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较
                //问题：缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加2入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;  //因为是存放到List中的，因此正常按顺序输出就是对应的后缀表达式对应的List
    }

    //方法：将中缀表达式转成对应的List
    //s = "1+((2+3)×4)-5"
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; //做对多位数的拼接工作
        char c; //每遍历到一个字符就放到c中
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数的问题
                str = "";  //先将str置成空串  '0'[48] -> '9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分隔
        String[] split = suffixExpression.split(" "); //字符串.split(参数)将字符串按指定方式分隔，返回字符串数组
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
    *   1) 从左至右扫描，将3和4压入堆栈；
        2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
        3) 将5入栈；
        4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
        5) 将6入栈；
        6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    * */

    public static int calculate(List<String> ls) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {  //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());    //Integer.parseInt("");把字符串转整数
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}

