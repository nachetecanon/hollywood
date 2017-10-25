function() {
    var DashboardGenerator = Java.type('net.pi.platform.hollywood.karate.utility.generator.DashboardGenerator');
    var dashGenerator = new DashboardGenerator();
    return dashGenerator.generateValidDashboard();
}
