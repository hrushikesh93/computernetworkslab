

set ns [new Simulator]

set tf [open mnt_code.tr w]  
$ns trace-all $tf

set nf [open mnt_code.nam w]
$ns namtrace-all $nf

set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]


$n1 label "ETH1"
$n2 label "ETH2"
$n3 label "ETH3"
$n4 label "ETH4"


$ns color 1 "green"
$ns color 2 "red"


$ns make-lan "$n1 $n2 $n3 $n4" 10Mb 10ms LL Queue/DropTail Mac/802_3



set ETH1 [ new Agent/TCP ]
$ns attach-agent $n1 $ETH1

set ftp1 [ new Application/FTP ]
$ftp1 attach-agent $ETH1


set ETH2 [ new Agent/TCPSink ]
$ns attach-agent $n2 $ETH2


set ETH3 [ new Agent/TCPSink ]
$ns attach-agent $n3 $ETH3


set ETH4 [ new Agent/TCP ]
$ns attach-agent $n4 $ETH4

set ftp4 [ new Application/FTP ]
$ftp4 attach-agent $ETH4


$ETH1 set class_ 1
$ETH4 set class_ 2


$ns connect $ETH1 $ETH2
$ns connect $ETH4 $ETH3


set file1 [ open file1.tr w ]
$ETH1 attach $file1 

$ETH1 trace cwnd_ 
$ETH1 trace maxcwnd_ 10

set file2 [ open file2.tr w ]
$ETH4 attach $file2

$ETH4 trace cwnd_ 
$ETH4 trace maxcwnd_ 10


proc finish { } {
global ns tf nf
exec nam mnt_code.nam &
exec awk -f get_congestion.awk file1.tr > cong1 &
exec awk -f get_congestion.awk file2.tr > cong2 &

$ns flush-trace
close $tf
close $nf
exit 0
}



$ns at 0.1 "$ftp1 start"
$ns at 1.0 "$ftp1 start"
$ns at 2.0 "$ftp1 start"
$ns at 3.0 "$ftp1 start"

$ns at 0.1 "$ftp4 start"
$ns at 1.0 "$ftp4 start"
$ns at 2.0 "$ftp4 start"
$ns at 3.0 "$ftp4 start"

$ns at 10.0 "finish"
$ns run
