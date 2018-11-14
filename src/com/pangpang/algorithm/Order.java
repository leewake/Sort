package com.pangpang.algorithm;

/*思路：
 * 设置三个标记指针:iZero, iOne, iTwo
 * 令iZero从前往后遍历,指向第一个非0的位置,iTwo从后往前遍历,指向第一个非2位置然后iOne从iZero开始往后遍历:
 * 遇到0就和iZero交换,iZero++;遇到1则iOne++;遇到2就和iTwo交换,iTwo向前滑动到下一个非2的位置,交换后还
 * 要重新检查iOne的值;直到iOne与iTwo相遇。一次遍历,复杂度是O(n),因为每次操作都是的数组更为有序,所以效率高一些.
 * Before...
 *1 2 0 0 2 1 2 1 1 0 2 2 1 0
 *After...
 *0 0 0 0 1 1 1 1 1 2 2 2 2 2
 */
public class Order {
    public int arr[] = {1,2,0,0,2,1,2,1,1,0,2,2,1,0};
    public int iLength;
    public int iZero, iOne, iTwo;//这三个量表示表示含有0,1,2的各自的数量
    void swap(int x, int y)
    {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    void sort()
    {       //希望最左边是0，从前往后找0
        while(arr[iZero] ==0)
        {
            iZero++;
            iOne++;
        }
        //希望最右边是2，从后往前找2
        while(arr[iTwo] == 2)
        {
            iTwo--;
        }
        while(iOne<=iTwo) //想要当前指针与末尾指针相遇
        {      //iOne本质上相当于是当前指针
            //arr[iOne]==2则交换到右边去，想要iOne指向一个从前向后的为1的位置
            if (arr[iOne]==2)
            {
                swap(iOne,iTwo);
                //想要iTwo指向一个从后向前的非2的位置
                iTwo--;
                //交换后如果存在arr[iTwo]==2则从后向前移至非2的位置
                while(arr[iTwo]==2)
                {
                    iTwo--;
                }
            }
            //arr[iOne]==1只移动不交换
            while(arr[iOne]==1)
            {
                iOne++;
            }
            //arr[iOne]==0则交换到左边去，想要iOne指向一个从前向后的为1的位置
            if (arr[iOne]==0)
            {
                swap(iOne, iZero);
                iZero++;
                iOne++;
            }
        }
    }

    public void sortColors(int[] nums) {
        //时间复杂度：O（n）空间复杂度:O（1）[只遍历一遍完成题目的要求]
        int zero = -1;          //nums[0...zero] == 0;
        int two = nums.length;  //nums[two,n-1] == 2;

        for(int i = 0;i < two;){
            if(nums[i]==1) {
                i++;
            }else if(nums[i] == 2) {
                //two--;
                int temp = nums[i];
                nums[i] = nums[--two];
                nums[two] = temp;
            }else {
                if(nums[i] == 0){
                    //zero++;
                    int tmp = nums[++zero];
                    nums[zero] = nums[i];
                    nums[i] = tmp;
                    i++;

                }else{
                    System.out.println("您输入的数组的数据有误！！！");
                }
            }
        }
    }

    public static void main(String[] args) {
        Order order = new Order();
        order.iLength = order.arr.length;
        order.iZero=0;
        order.iOne=0;
        order.iTwo=order.iLength-1;

        System.out.println("Before...");
        for (int i=0;i<order.iLength;i++)
        {
            System.out.print(order.arr[i]+" ");
        }
//        order.sort();
        order.sortColors(order.arr);
        System.out.println("\nAfter...");
        for (int i=0;i<order.iLength;i++)
        {
            System.out.print(order.arr[i]+" ");
        }
    }
}
