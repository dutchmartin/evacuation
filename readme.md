# Evacuating People

It is 2050 and due to climate change sea water levels have risen significantly. A dangerous hurricane is approaching the Netherlands and if the dikes break the whole western part of the country will be flooded. We need to evacuate the people quickly! There are several roads connecting the endangered cities with the eastern part of the country. The goal is to evacuate everybody from the endangered cities in the west to a few designated cities in the east. We need to evacuate everybody as fast as possible, and your task is to find out what is the maximum numb er of people that can b e evacuated each hour given the capacities of all the roads.

## Input
The first line of the input contains integers n <= 30 000 and m <= 100 000. The number of cities and the number of roads respectively. The second line of the input contains integers e and d, denoting the number of endangered cities in the west and the numb er of designated cities in the east (so e + d <= n; note that we may also have cities that are neither endangered nor designated). The third line lists the e endangered places, using 0-based indices of the corresponding cities. Similarly, the fourth line lists the d designated cities. Each of the next m lines contains three integers u , v and c describing a particular road: start of the road, end of the road and the number of people that can b e transported through this road in this direction in one hour (roads are one-way as we obviously cannot violate traffic rules, but there might be both roads from u to v and from v to u ).

## Output

You should return (via stdout) the maximum number of people that can be evacuated per hour. (Note that you might not evacuate people from a specific city at all while still evacuating the maximum number of people per hour.)

## Examples
### Sample input 1
```
4 4
1 1
0
3
0 1 400
0 2 600
1 3 500
2 3 700

```
### Sample output 1
```
1000
```
### Sample input 2
```
7 8
2 2
0 4
6 3
0 4 100
4 1 600
4 3 500
5 4 300
5 6 100
5 2 500
1 2 300
2 3 750
```

### Sample output 1
```
800
```