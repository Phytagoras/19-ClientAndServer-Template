import model.List;

public class Test{
    public static void main(String[] args){
        Test test = new Test();
        test.start();
    }

    private void start(){
        int[] array = new int[]{1, 25, 33, 17, 23,2, 67, 2,44 , 5, 86, 7,2, 4};
   /*     showArr(array);
        int[] newArray = new int[array.length];
        for(int i = 0; i < array.length; i++){
            int tmp = 0;
            for (int u = 0; u < array.length; u++){
                if(array[u] < array[tmp]) tmp = u;
            }
            newArray[i] = array[tmp];
            array[tmp] = Integer.MAX_VALUE;
        }
        array = newArray;
        showArr(array);*/

        List<Integer> list = new List<>();
        for(int i :
                array){
            list.append(i);
        }
        showList(list);

        //----------

        List<Integer> newList = new List<>();
        int counter = 0;
        list.toFirst();
        while(list.hasAccess()){
            counter++;
            list.next();
        }
        for(int i = 0; i < counter; i++){
            list.toFirst();
            Integer tmp = list.getContent();
            while(list.hasAccess()){
                if(list.getContent() < tmp){
                    tmp = list.getContent();
                }
                list.next();
            }
            newList.append(tmp);
            list.toFirst();
            while(list.hasAccess()){
                if(list.getContent() == tmp){
                    list.remove();
                    break;
                }
                list.next();
            }


            }
        list = newList;

        showList(newList);

    }
    private void showArr(int[] arr){
        for(int i :
                arr){
            System.out.println(i);
        }
    }
    private void showList(List<Integer> list){
list.toFirst();
while(list.hasAccess()){
    System.out.println(list.getContent());
    list.next();
}
    }
}
