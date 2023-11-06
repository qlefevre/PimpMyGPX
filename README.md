[![Create executables](https://github.com/qlefevre/PimpMyGPX/actions/workflows/create-release.yml/badge.svg)](https://github.com/qlefevre/PimpMyGPX/actions/workflows/create-release.yml)

# PimpMyGPX

Afficher l'aide
```
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

Modifier l'heure de début de la course
```
pmgpx --start-time hh:mm
```
Dans cet exemple, la course commence à 9h50 (Paris GMT+2).
```xml
 <trkpt lat="50.4737550" lon="3.3012980">
    <time>2023-10-15T07:50:39Z</time>
```
Pour changer l'heure de début à 10h50, on tape cette commande :
```
pmgpx --start-time 10:50
```
On obtient le résultat suivant 
```xml
 <trkpt lat="50.4737550" lon="3.3012980">
    <time>2023-10-15T08:50:39Z</time>
```
