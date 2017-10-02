function() {
    var DashboardGenerator = Java.type('karate.utility.generator.DashboardGenerator');
    var dashGenerator = new DashboardGenerator();
    return dashGenerator.generateValidDashboard();
}
