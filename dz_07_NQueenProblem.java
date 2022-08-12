/* На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга. */

class NQueenProblem {
    final int N = 8;

    /* Функция для вывода результата в консоль */
    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    /* Функция для проверки можно ли ферзя разместить
    на позицию board[row][col]. Заметим, что эта функция
    вызывается после того как позиции "col" ферзей уже
    раположились между 0 и col -1. Поэтому нам нужна проверка
    только левой стороны на возможность атаки ферзей */
    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;

        /* Проверка строки row с левой стороны */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Проверка диагонали влево вверх */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Проверка диагонали влево вниз */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    /* Рекурсивная функция для решения основной задачи */
    boolean solveNQUtil(int board[][], int col)
    {
		/* Если все ферзи уже расположены, возвращаем true */
        if (col >= N)
            return true;

		/* Рассмотрим эту колонку и попробуем разместить ферзя
		на все строки по очереди */
        for (int i = 0; i < N; i++)
			/* Проверим можно ли разместить ферзя на позицию
			board[i][col] */
            if (isSafe(board, i, col)) {
                /* Размещаем ферзя на позицию board[i][col] */
                board[i][col] = 1;

                /* рекурсивно распологаем остальные ферзи */
                if (solveNQUtil(board, col + 1) == true)
                    return true;

				/* Раз выполнение кода долшло до сюда, значит
				расположение ферзя на позицию board[i][col]
				не приводит к решению задачи,
				поэтому удаляем ферзя из board[i][col] */
                board[i][col] = 0; // BACKTRACK
            }

		/* Если ферзь не может быть расположен на всех строках
		нашей колонки col, поэтому возвращаем false */
        return false;
    }

    /* Эта функция решает задачу расположения ферзей
    используя Backtracking. В основном используется
    функция solveNQUtil () для решения задачи.
    Она возвращает false если ферзей не получается
    разместить, иначе возращает true и выводит
    расположение ферзей в виде цифр 1. */
    boolean solveNQ()
    {
        int board[][] = new int[N][N];

        if (solveNQUtil(board, 0) == false) {
            System.out.print("Решение не найдено");
            return false;
        }

        printSolution(board);
        return true;
    }

    // driver program to test above function
    public static void main(String args[])
    {
        NQueenProblem Queen = new NQueenProblem();
        Queen.solveNQ();
    }
}
// Этот код предоставлен Abhishek Shankhadhar,
// комментарии на русский перевел Шахэмир Шахэмиров
