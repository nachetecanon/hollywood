function() {
    var DashboardGenerator = Java.type('utility.generator.DashboardGenerator');
    var dashGenerator = new DashboardGenerator();
    return dashGenerator.generateValidDashboard();
}
