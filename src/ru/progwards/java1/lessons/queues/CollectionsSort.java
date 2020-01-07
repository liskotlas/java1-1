package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
/*    Сравнение методов сортировки коллекций

1.1 Реализовать метод public static void mySort(Collection<Integer> data) - переделать алгоритм
из класса ArraySort из ДЗ про массивы, на коллекции. Не использовать встроенные методы сортировок

1.2 Реализовать метод public static void minSort(Collection<Integer> data) по следующему алгоритму
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую

1.3 Реализовать метод public static void collSort(Collection<Integer> data) используя метод sort из Collections

1.4 Реализовать метод public static Collection<String> compareSort() в котором сравнить производительность
методов и вернуть их имена, отсортированные в порядке производительности, первый - самый быстрый. В случае
равенства производительности первым вернуть "collSort".
*/

    public static void mySort(Collection<Integer> data) { // буду реализовывать простейший ArraySort.sort3, т.к. эту функцию использовать не предвидится
        // лучше бы, конечно, не делать лишних свопов в типах коллекций, где доступ по индексу осуществляется быстро
/*
1257 4244 5634
701 4172 5524
693 4186 5584
700 4161 5606
701 4242 5675
*/
        /*
        for (int i = 0; i < data.size(); i++) {
            // найдем в остатках максимальный
            for (int j = i + 1; j < data.size(); j++) {
                if (data[i] > a[i]) {
                    int tmpValue = a[i];
                    a[i] = a[j];
                    a[j] = tmpValue;
                }
            }
        }
        */
        Iterator<Integer> it1 = data.iterator();
        int idx1 = 0;
        while (it1.hasNext()) {
            Integer num1 = it1.next();
            Iterator<Integer> it2 = data.iterator();
            int idx2 = 0;
            Integer num2;
            for (int i = idx1; i >= 0; i--) it2.next();
            while (it2.hasNext()) {
                num2 = it2.next();
                if (num1.compareTo(num2) > 0) {
                    Collections.swap((List)data, idx1, idx2); // List!? ... а как иначе их поменять местами, не понимаю
                    num1 = num2;
                }
                idx2++;
            }
            idx1++;
        }
    }

    public static void minSort(Collection<Integer> data) {
/*
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую
*/
/*
1296 3948 5391
780 3864 5017
784 3858 5078
770 4111 5102
785 3860 5393
801 4121 5263
*/
        ArrayList<Integer> list = new ArrayList(data);
        ArrayList<Integer> result = new ArrayList(data.size());

        while (list.size() > 0) {
            Integer min = Collections.min(list);
            result.add(min);
            list.remove(min);
        }

        data.clear();
        data.addAll(result);
    }

    public static void collSort(Collection<Integer> data){
/*
15 0 0
15 0 0
0 16 0
0 0 0
16 0 0
*/
        Collections.sort((List)data);
    }

    public static void randomFill(Integer[] a, int from, int to) {
        for (int i = a.length - 1; i >= 0; i--) a[i] = (int) ((to + from) * Math.random()) + from;
    }

    public static Collection<String> compareSort1() {
        //сравнить производительность методов и вернуть их имена, отсортированные в порядке производительности,
        // первый - самый быстрый. В случае равенства производительности первым вернуть "collSort"
        for (int cnt = 1; cnt <= 10; cnt++) {
            Integer[] a1 = new Integer[100];
            randomFill(a1, 100, -100);
            Integer[] b1 = new Integer[50_000];
            randomFill(b1, 50_000, -50_000);
            Integer[] c1 = new Integer[100_000];
            randomFill(c1, 100_000, -100_000);
            int aCnt = 10000;
            int bCnt = 3;
            int cCnt = 1;
            long time0 = System.currentTimeMillis();
            for (int i = aCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(a1));
                collSort(l);
            }
            long time1 = System.currentTimeMillis();
            for (int i = bCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(b1));
                collSort(l);
            }
            long time2 = System.currentTimeMillis();
            for (int i = cCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(c1));
                collSort(l);
            }
            long time3 = System.currentTimeMillis();
            System.out.println((time1 - time0) + " " + (time2 - time1) + " " + (time3 - time2));
        }
        return null;
    }

    public static Collection<String> compareSort() {
        //сравнить производительность методов и вернуть их имена, отсортированные в порядке производительности,
        // первый - самый быстрый. В случае равенства производительности первым вернуть "collSort"
        class Experiment {
            String name;
            long time;

            Experiment(String name, long time) {
                this.name = name;
                this.time = time;
            }
        }
        class SortExpByTime implements Comparator<Experiment> {
            public int compare(Experiment a, Experiment b) {
                return Long.compare(a.time, b.time);
            }
        }
        long t3 = 0;
        long t2 = 0;
        long t1 = 0;
        for (int cnt = 1; cnt <= 2; cnt++) {
            Integer[] c1 = new Integer[5_000];
            randomFill(c1, 5_000, -5_000);
            int cCnt = 1;
            long time0 = System.nanoTime();
            for (int i = cCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(c1));
                collSort(l);
            }
            long time1 = System.nanoTime();
            for (int i = cCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(c1));
                minSort(l);
            }
            long time2 = System.nanoTime();
            for (int i = cCnt; i > 0; i--) {
                ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(c1));
                mySort(l);
            }
            long time3 = System.nanoTime();
            t1 += time1 - time0;
            t2 += time2 - time1;
            t3 += time3 - time2;
            //System.out.println(t1 + " " + t2 + " " + t3);
        }

        ArrayList<Experiment> exp = new ArrayList<Experiment>(3);
        exp.add(new Experiment("collSort", t1));
        exp.add(new Experiment("minSort", t2));
        exp.add(new Experiment("mySort", t3));
        Collections.sort(exp, new SortExpByTime());

        ArrayList<String> result = new ArrayList<String>(3);
        for (Experiment e : exp) {
            result.add(e.name);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(compareSort());
    }

}
