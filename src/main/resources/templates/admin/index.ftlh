<#import "../parts/page.ftlh" as page>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/modal.ftlh" as modal>
<@page.page "Админ-Панель">
    <@navbar.navbar 1></@navbar.navbar>
    <div class="grey-bg container-fluid">
        <section id="minimal-statistics">
            <div class="row">
                <div class="col-12 mt-3 mb-1">
                    <h4 class="text-uppercase">Основная статистика.</h4>
                    <p>Статистика в наглядных карточках.</p>
                    <button type="button" class="btn btn-success" onclick="getGeneralInfo();"><i class="fas fa-sync-alt"></i></button>
                    <button type="button" class="btn btn-success" onclick="drawChart();"><i class="fas fa-sync-alt"></i></button>
                    <@modal.updatePanel ""></@modal.updatePanel>
                    <div class="alert alert-success" role="alert" id="successInfo" style="display: none"></div>
                    <div class="alert alert-success" role="alert" id="ErrorInfo" style="display: none"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-hand-holding-heart primary font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="countries">${info.services}</h3>
                                        <span>Услуг</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-users warning font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="planes">${info.employee}</h3>
                                        <span>Мастеров</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="fas fa-receipt font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3 id="bookedTickets">${info.bookedRecords}</h3>
                                        <span>Забронированных билетов</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="success" id="roles">${info.roles}</h3>
                                        <span>Ролей</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-user-tag success font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="warning" id="users">${info.users}</h3>
                                        <span>Пользователей</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-users warning font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="primary" id="tickets">${info.freeRecords}</h3>
                                        <span>Билетов на продаже</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="fas fa-ticket-alt primary font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <script src="adminPanel.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        // Load the Visualization API and the piechart package.
        google.charts.load('current', {'packages':['corechart']});

        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        function drawChart() {
            // Create the data table.
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Roles');
            data.addColumn('number', 'Count');
            data.addRows([
                ['ADMIN', 1],
                ['USER', 2]
            ]);

            // Set chart options
            var options = {'width':400,
                'height':300};
            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
    <body>
    <div id="chart_div" style="width: 300px; height:400px"></div>
    </body>
</@page.page>