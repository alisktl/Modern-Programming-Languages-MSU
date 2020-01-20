# Task1: QuickSort

## Формулировка задания
Реализовать QuickSort с использованием компаратора. Реализация должна использовать хотя бы часть следующих идей
- 3-частное разбиение
- один рекурсивный вызов вместо двух
- переключение на сортировку вставками на массивах небольшого размера
- выбор опорного элемента как медианы-из-3 (или по методу Tukey's ninther)

Реализовать компаратор, подсчитывающий число сравнений. Запустить на разных размерах массивов и сравнить результаты стандартного метода сортировки и реализованного самостоятельно (если реализовано переключение на сортировку вставками и/или специальный выбор опорного элемента - как работает с этой опцией и без нее).


## Количество сравнений
На картинке показана зависимость количества сравнений QuickSort'а и стандартного Arrays.sort'а от размера массива

![alt text](https://github.com/alisktl/Modern-Programming-Languages-MSU/blob/master/Task1/QuickSort_vs_Arrays.sort.png)