package Seminar_03;

/* Шахматную доску размером NxN обойти конём так,
   чтобы фигура в каждой клетке была строго один раз */

class KnightTour {
    static int N = 8;

    /* xMove[] и yMove[] определяют следующий ход коня.
    xMove[] для следующего хода по x координате
    yMove[] для следующего хода по y координате */
    static int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

    /* Функция для проверки координат x, y
    что они внутри поля NxN и ячейка свободна */
    static boolean isSafe(int x, int y, int sol[][])
    {
        return (x >= 0 && x < N && y >= 0 && y < N
                && sol[x][y] == -1);
    }

    /* Функция для вывода в консоль матрицы решения sol[N][N] */
    static void printSolution(int sol[][])
    {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.printf(" %2d ", sol[x][y]);
            System.out.println();
        }
    }

    /* Эта функция решает задачу прохождения конем
    используя бэктрекинг. В основном тут применяется
    функция solveKTUtil() для решения задачи. Она
    возвращяет false если нет возможного решения,
    иначе возвращает true и выводит результат пути */
    static boolean solveKT()
    {
        int sol[][] = new int[N][N];

        /* Создаем матрицу решения задачи */
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

        // Изначальная позиция коня на доске
        sol[0][0] = 0;

		/* Начинаем с позиции 0,0 и затем исследуем всевозможные
		пути, используя функцию solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol)) {
            System.out.println("Задача не имеет решения");
            return false;
        }
        else
            printSolution(sol);

        return true;
    }

    /* Рекурсивная функия для решения задачи
    прохождения коня по доске */
    static boolean solveKTUtil(int x, int y, int movei,
                               int sol[][])
    {
        int k, next_x, next_y;
        if (movei == N * N)
            return true;

		/* Пробуем всевозможные ходы начиная с текущей координаты
			x, y */
        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1,
                        sol))
                    return true;
                else
                    sol[next_x][next_y]
                            = -1; // backtracking
            }
        }

        return false;
    }

    /* Driver Code */
    public static void main(String args[])
    {
        // Вызов функции
        solveKT();
    }
}
// Этот код предоставлен Abhishek Shankhadhar
// комментарии на русский перевел и по мелочи доработал Шахэмир Шахэмиров
