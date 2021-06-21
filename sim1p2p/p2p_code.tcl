
set ns [new Simulator]

set tf [open p2p_code.tr w]  
$ns trace-all $tf

set nf [open p2p_code.nam w]
$ns namtrace-all $nf


set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]


$n0 label "Source1/udp0"
$n1 label "Source2/udp1"
$n2 label "IntermidiateNode/Router"
$n3 label "Destination Node"


$ns duplex-link $n0 $n2 10Mb 300ms DropTail
$ns duplex-link $n1 $n2 10Mb 300ms DropTail
$ns duplex-link $n2 $n3 1Mb  300ms DropTail


$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n1 $n2 10
$ns set queue-limit $n2 $n3 5


$ns color 1 "green"
$ns color 2 "yellow"


set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

 
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0


set null3 [new Agent/Null]
$ns attach-agent $n3 $null3

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1

set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1


$udp0 set class_ 1
$udp1 set class_ 2


$ns connect $udp0 $null3
$ns connect $udp1 $null3


$cbr1 set packetSize_ 200Mb

$cbr1 set interval_ 0.001



proc finish { } {
global ns tf nf
$ns flush-trace
exec nam p2p_code.nam
close $tf
close $nf
exit 0
}


$ns at 0.1 "$cbr0 start"
$ns at 0.5 "$cbr1 start"
$ns at 10.0 "finish"
$ns run


