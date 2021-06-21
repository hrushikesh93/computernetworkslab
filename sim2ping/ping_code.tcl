
set ns [new Simulator]
set tf [open ping_code.tr w]  
$ns trace-all $tf
set nf [open ping_code.nam w]
$ns namtrace-all $nf
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
$n2 label "Router"
$n0 label "Ping0"
$n1 label "Ping1"
$n4 label "Ping4"
$n5 label "Ping5"
$n6 label "Ping6"
$ns color 1 "red"
$ns color 2 "blue"
$ns duplex-link $n0 $n2 10Mb 300ms RED
$ns duplex-link $n4 $n2 7Mb 300ms RED
$ns duplex-link $n5 $n2 10Mb 300ms RED
$ns duplex-link $n1 $n2 7Mb 300ms RED
$ns duplex-link $n2 $n6 3Mb 300ms RED
$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n2 $n1 10
$ns set queue-limit $n4 $n2 10
$ns set queue-limit $n5 $n2 10
$ns set queue-limit $n6 $n2 10
set ping0 [new Agent/Ping]
$ns attach-agent $n0 $ping0 
set ping1 [new Agent/Ping]
$ns attach-agent $n1 $ping1
set ping4 [new Agent/Ping]
$ns attach-agent $n4 $ping4
set ping5 [new Agent/Ping]
$ns attach-agent $n5 $ping5
set ping6 [new Agent/Ping]
$ns attach-agent $n6 $ping6
$ping0 set packetSize_ 50000
$ping0 set interval_ 0.0001
$ping5 set packetSize_ 60000
$ping5 set interval_ 0.00001
$ping1 set packetSize_ 60000
$ping1 set interval_ 0.001
$ping0 set class_ 1
$ping4 set class_ 1
$ping5 set class_ 2
$ping6 set class_ 2
$ping1 set class_ 2
$ns connect $ping0 $ping4 
$ns connect $ping5 $ping6 
$ns connect $ping1 $ping6
Agent/Ping instproc recv { from rtt } {
$self instvar node_
puts "The node [ $node_ id ] received a reply $from with a RTT of $rtt"
}
proc finish { } {
global ns nf tf
exec nam ping_code.nam &
exec awk -f drop_count.awk ping_code.tr &
$ns flush-trace
close $tf
close $nf
exit 0
}
proc sendPingPacket { } {
global ns ping0 ping5 ping1
set interval_ 0.001
set now [ $ns now ]
$ns at [ expr $now + $interval_ ] "$ping0 send"
$ns at [ expr $now + $interval_ ] "$ping5 send"
$ns at [ expr $now + $interval_ ] "$ping1 send"
$ns at [ expr $now + $interval_ ] "sendPingPacket"
}
$ns at 0.01 "sendPingPacket"
$ns at 5.0 "finish"
$ns run
