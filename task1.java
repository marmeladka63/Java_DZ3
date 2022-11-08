// Волновой алгоритм

import java.util.Queue;
import java.util.ArrayDeque;

class Node {
    int x, y, dist; // x,y - координаты ячейки матрицы, dist -мин расстояние от источника

    Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Main {
    private static final int[] row = {-1,0,0,1};  //массивы перемещения из ячейки. Методы нельзя переопределить 
    private static final int[] col = {0,-1,1,0};

    private static boolean isValid(int [][] mat, boolean[][] visited, int row, int col){ 
        // Функция проверки перехода на позицию (строка, столбец) от текущей позиции. Возвращает folse если позиция недопустимая,
        // или имеет значение 0 или посещено.

               return (row >=0) && (row < mat.length) && (col >=0) && (col < mat[0].length) && mat[row][col] ==1 && !visited[row][col];


    }
    private static int findShortestPathLength (int[][] mat, int i, int j, int x, int y) {
        // Нахождение кратчайшего маршрута в матрице mat из источника ячейка (i,j) в ячейку назначения (x, y)
        if (mat == null || mat.length ==0 || mat[i][j]==0 || mat[x][y]==0){ // Функция проверки неверного ввода
        }
        // матрица NxM
        int M = mat.length;
        int N = mat[0].length;
        // создаем матрицу для отслеживания посещенных ячеек
        boolean [][] visited = new boolean [M][N];

        // создаем пустую очередь
        Queue<Node> q = new ArrayDeque<>();
        // помечаем исходную ячейку как посещенную и ставим исходный узел в очередь 
        visited[i][j] = true;
        q.add(new Node(i,j,0));
        // сохраняем длинну самого длинного пути от источника к месту назначения
        int min_dist = Integer.MAX_VALUE;

        // запускаем цикл, пока очередь не станет пустой
        while(!q.isEmpty()){
            Node node = q.poll(); // обрабатываем и удаляем первый узел
            i = node.x; // координаты текущей ячейки
            j = node.y;
            int dist = node.dist; // мин расстояние от источника
            if (i==x && j==y){
                min_dist = dist; // если пункт назначения найден, обновляем минимальную дистанцию и останавливаемся.
                break;
            }
            for (int k =0; k <4; k++){
                // проверяем можно ли выйти на позицию i+row[k], j+col[k] от текущей позиции
                if(isValid(mat, visited, i+row[k], j+col[k])){
                    // отметиv следующую ячейку как посещенную и поставить ее в очередь
                    q.add(new Node(i+row[k],j+col[k],dist+1 ));


                }
            }


        }
        if (min_dist != Integer.MAX_VALUE) {
            return min_dist;
        }
        return -1;



    }
    public static void main(String[] args) {
        int[][] mat = {
            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
            { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
            { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
            { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
        };
        int min_dist = findShortestPathLength(mat, 0, 0, 7, 7);
        if(min_dist != -1){
            System.out.println("Кратчайший путь от источника к месту назначения имеет длинну  " + min_dist);
        }
        else {
            System.out.println(("Пути нет"));
        }
        }
        
        
    }

