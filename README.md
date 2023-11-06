[![Create executables](https://github.com/qlefevre/PimpMyGPX/actions/workflows/create-release.yml/badge.svg)](https://github.com/qlefevre/PimpMyGPX/actions/workflows/create-release.yml)

# PimpMyGPX

## Interface en ligne de commande

Le nom de la commande `pmgpx`.

Le programme se nomme :
- pmgpx.exe sous windows
- pmgpx sous macos et linux

Les optiosn sont :
- [`--help` - Afficher l'aide](#--help---afficher-l-aide)
- [`--start-time` - Modifier l'heure de début de la course](#--start-time---modifier-l-heure-de-d-but-de-la-course)
- [`--finish-time` - Modifier l'heure de fin de la course](#--finish-time---modifier-lheure-de-fin-de-la-course)
- [`--date` - Modifier la date de la course](#--date---modifier-la-date-de-la-course)

### `--help` - Afficher l'aide

```console
pmgpx --help
```
L'aide suivante apparaîtra
```
C:\Users\Forrest Gump\Downloads>pmgpx
PimpMyGPX 1.0
usage: pmgpx [OPTION]... [FILE]
Do something useful with a GPX file
 -s,--start-time <hour>    Change start time in hh:mm or hh:mm:ss format
 -f,--finish-time <hour>   Change finish time in hh:mm or hh:mm:ss format
 -d,--date <date>          Change the date in yyyy-mm-dd format
 -lat,--latitude <lat>     Change the latitude by adding <lat> to all waypoints
 -lon,--longitude <lon>    Change the longitude by adding <lon> to all waypoints
 -o,--output <file>        Set output file
 -h,--help                 Display help
Please report issues at https://github.com/qlefevre/PimpMyGPX
```

### `--start-time` - Modifier l'heure de début de la course
```console
pmgpx --start-time hh:mm
```
Dans cet exemple, la course commence à 9h50 (Paris GMT+2) : `<time>2023-10-15T07:50:39Z</time>`  
Pour changer l'heure de début à 10h50, on tape cette commande :
```
pmgpx --start-time 10:50
```
On obtient le résultat suivant : `<time>2023-10-15T08:50:39Z</time>`

### `--finish-time` - Modifier l'heure de fin de la course
```console
pmgpx --finish-time hh:mm
```
Dans cet exemple, la course commence à 10h18 (Paris GMT+2) : `<time>2023-10-15T08:18:40Z</time>`  
Pour changer l'heure de fin à 10h30, on tape cette commande :
```console
pmgpx --finish-time 10:30
```
On obtient le résultat suivant : `<time>2023-10-15T08:30:40Z</time>`

### `--date` - Modifier la date de la course
```console
pmgpx --date yyyy-mm-dd format
```
Dans cet exemple, la course se déroule le 15/10/2023 : `<time>2023-10-15T08:18:40Z</time>`  
Pour changer la date, on tape cette commande :
```console
pmgpx --finish-time 2023-07-14
```
On obtient le résultat suivant : `<time>2023-07-14T08:30:40Z</time>`
