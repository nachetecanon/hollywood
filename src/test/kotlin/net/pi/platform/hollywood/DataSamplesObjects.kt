package net.pi.platform.hollywood

import net.pi.platform.hollywood.model.Dashboard
import net.pi.platform.hollywood.model.Link
import net.pi.platform.hollywood.model.Position
import net.pi.platform.hollywood.model.Widget

class DataSamplesObjects {

    companion object Dashboards {

        fun getDashboard(): Dashboard {
            return Dashboard(
                    id = null,
                    name = "longerName",
                    hidden = false,
                    widgets = listOf(Widget(id = null, name = "name-widget", bookmarked = true, visualization = null,
                            app = null,
                            type = null,
                            position = Position(x = 0, y = 1, rows = 2, cols = 2))),
                    position = Position(x = 0, y = 1, rows = 2, cols = 2),
                    link = Link(url = "google.es", type = "URL"),
                    bookmarked = true,
                    createdDate = null,
                    lastModifiedDate = null,
                    image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAACcQAAATGCAYAAAACbDX2AAABS2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDAgNzkuMTYwNDUxLCAyMDE3LzA1LzA2LTAxOjA4OjIxICAgICAgICAiPgogPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIi8+CiA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgo8P3hwYWNrZXQgZW5kPSJyIj8+LUNEtwAAIABJREFUeJzs3U+oZvV9x/HvOfdeHYM7IetmHbNWug0JbSVQ7NJ00RJMtJBFIySLZpFdu2lXLdRNSgvVrDSKiIWEQJJGa2wkiWCiNBUSLSoOBmbuzPPnnC4OT+51cmfm3nHG8zyf3+sFw2x/53zvPc/vPr/3vU+3Wq3GAgAAAAAAAAAAgB3Xz70AAAAAAAAAAAAAuBkEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABH2514AAAC7bRiqxnH6tyu6rqrvp/+5PjPOZ8bZNrMdhrlXcjZ9P/3j+jbz3bXv4c33Mb/PTPN57c1nxvmGYff2V2Z8erv4WlxlDw0AABuCOAAAzmy9rlouq1ar3Xtz+Li+r9rfrzo48IbxlVaracbrtRmn2nwPr1Zzr+TD2ds7mrGDvSPj+MHv4122tzfN9+Bg7pVsl3Gc5rtc7t5h/HFddzTj/cbfpRqGqsVi9/dXXXf0XN7bm3s128UeOl/SHnrz2mt/9UGb196U/dX+vhkft9lfrVa7P+P9/aNnNQAAtKhbrVY7/KM5AAAfpWGounx59wOakxwcVN1+u8OA9Xqa8a6/+X+S226b/rU+49VqmvEuBzQn6bqjGbduuZxmvMsH8Sfpuuk57VBviqYWi7wZ9/0049bCuOT91f7+NNPWo6lhqLp0KXN/ZQ89Wa+nGaftr6qmvdXtt8+9ivnZX+WzvwIAgCyCOAAATmW1mg550t4cPq7rqu64o92/ZrJYTIc8yfp+mnGrB/OXLk2Hecn29qYZt3gwP47TjBOjmuP296vOnWt3xoeHmVHNcS3FF63sr86da/cgfrmcZpys76cZt7qHvnx52kcns4fO30PbX+Xvrw4OphkDAEArBHEAAFxXCwd5x91xR3uHti0c5G10XdXHPtbegd7hYX4otdH304xbOtBr5SBvo8XwcRimGSf+9aGTtHBou1pNM23FuXPt/QWiFn7ZYKPVXyxpIZTaaHUPffFiO/urVvfQFy+2s7/a35+e1QAA0ILGfnwFAOCsNn+5pCUtBQdV0yFeKzFc1dGhR/Jf47lS6kfxXc0mHGpJ6kfxXc3mo+lasQkeW3ttSg6J1mvPqXTpX8NXavE5tVi0E8NVHc24pT10a8+tFvfQrT23Wnx/BwCAdgniAAC4qs3H77WolYOAYWhzxi19ba9WbQWPG+t1OyHCctlW8LixWrUTIiwWbR3WbiwWuSFCK69BV0r/eNiNcWznNei4lvZXLe0zjhuGdq57uWxnn3Hcet3Ozw7J+4xrafVnBwAA2iOIAwDgqhaLNg4tTzIMbRyAtHKgdZLVqo2DgJZn3EJE1Gp0sXH5cv7r1DC0czB9ksS4poVn09W0tL9KfzZdzXrdzoxbtVy2ERG1POMW3gewh557BQAAcOsJ4gAAONE4tnGYdS3pAcIwtBGEXUv61/hq1W50sZE+4+Uy/8DyWsYx/zmW/lp0PYmvVa3PNP367aHzr3+9biMIu5b0Gdtf5c84/bXoeloJ1AEAaJsgDgCAE61WbR8CVE1vEicfdnkDPP/r3Izz70H69Z1G+j1Ii8FuRNI9SH/dOY30kDX9mXQa63V2kG/G+cGYGeffg/TrO43k12IAAKgSxAEAcBXJIdhZJN+H5Gs7i+SDADOeDmtT78M4ZgcHp7Ve5x7KJ1/bWSQ9p1OfR2eVfB+Sr+0sku9D8rWdRfJ9SL620xqG3H3mMNhfVfk6BwAgnyAOAIATpb75fVbJbxKb8ST1Poyjg56N1BmnXteNSL0Xqdd1VknPMzOd2F/lM+N8qfch9bpuROq9SL2us/LLNQAApBPEAQBwIm+MTlIO4K+UFBd8WKn3wffwETPOlzrj1Ou6ESn3wvftJGWeJzHjSeqMzfeIGecz43ypMwYAgCpBHAAAXJM3iAG2X+qzOvW6gHyeX7D7fB8DAAC7TBAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEEcQAAAAAAAAAAAEQQxAEAAAAAAAAAABBBEAcAAAAAAAAAAEAEQRwAAAAAAAAAAAARBHEAAAAAAAAAAABEEMQBAAAAAAAAAAAQQRAHAAAAAAAAAABABEEcAAAAAAAAAAAAEQRxAAAAAAAAAAAARBDEAQAAAAAAAAAAEEEQBwAAAAAAAAAAQARBHAAAAAAAAAAAABEE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f9F+7n2fxyPpP/3rdc4/5557nvccAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAAAAAAAACUIIgDAAAAAAAAAACgBEEcAAAAAAAAAAAAJQjiAAAAAAAAAAAAKEEQBwAAAAAAAAAAQAmCOAAAAAAAAAAAAEoQxAEAAAAAAAAAAFCCIA4AAAAAAAAAAIASBHEAAAAA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AQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACYI4AAAAAAAAAAAAShDEAQAAAAAAAAAAUIIgDgAAAAAAAAAAgBIEcQAAAAAAAAAAAJQgiAMAAAAAAAAAAKAEQRwAAAAAAAAAAAAlCOIAAAAAAAAAAAAoQRAHAAAAAAAAAABACZO6HgAAAAAYHdde2+Rb305WrGhy++3JH+/peqJHZ7NpyezZyaJFbfbeO1m8Q9v1SAAAAAAATBCCOAAAAChk9erkoouanPOJXm68setpNlSTJFmwIDnh+H5e8uI2Pfe6BwAAAABgHVxGBgAAgCJWXN/kufv1ctKbKsRwD7jhhuT1b+hl/wNqrQsAAAAAgI1PEAcAAAAFfOPfmjzvgF6uW9F0Pcqo+clPmjx3/6F885t11wgAAAAAwIYRxAEAAMAE981/b3L00b3ce2/Xk4y+e+5JDj+yl+98RxQHAAAAAMD/J4gDAACACew3v0mOe00v96/uepKx86c/Jcce18vvbul6EgAAAAAAxhtBHAAAAExgbzu5lzvv7HqKsXf77clpp7msAQAAAADA/+XKMQAAAExQP/hhkyuuHNxHh37lq01+dt3grh8AAAAAgP9PEAcAAAAT1HnnicEcAwAAAAAAHkwQBwAAABNQv5+BvjvcWl+/3DEAAAAAAOABgjgAAACYgG64ocmdd3Y9RfduvTX5xS+6ngIAAAAAgPFCEAcAAAATkAjsATfd5C5xAAAAAACMEMQBAADABHTH/3Q9wfhx1x+6ngAAAAAAgPFCEAcAAABMaKtWdT0BAAAAAADjhSAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoJosfnoAABaESURBVARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAA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ACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAAAAAAAAAACAEgRxAAAAAAAAAAAAlCCIAwAAAAAAAAAAoARBHAAAAAAAAAAAACUI4gAAAAAAAAAAAChBEAcAAAAAAAAAAEAJgjgAAAAAAAAAAABKEMQBAAAAAAAAAABQgiAOAID/becOSgCAARiIwfx77kTsMTgSBRVwFAAAAAAAACBBEAcAAAAAAAAAAECCIA4AAAAAAAAAAIAEQRwAAAAAAAAAAAAJgjgAAAAAAAAAAAASBHEAAAAAAAAAAAAkCOIAAAAAAAAAAABIEMQBAAAAAAAAAACQIIgDAAAAAAAAAAAgQRAHAAAAAAAAAABAgiAOAAAAAAAAAACABEEcAAAAAAAAAAAACYI4AAAAAAAAAAAAEgRxAAAAAAAAAAAAJAjiAAAAAAAAAAAASBDEAQAAAAAAAAAAkCCIAwAAAAAAAAAAIEEQBwAAAAAAAAAAQIIgDgAAAAAAAAAAgARBHAAAAAAAAAAAAAmCOAAAAAAAAAAAABIEcQAAAAAAAAAAACQI4gAAAAAAAAAAAEgQxAEAAAAAAAAAAJAgiAMAAAAAAAAAACBBEAcAAAAAAAAAAECCIA4AAAAAAAAAAIAEQRwAAAAAAAAAAAAJgjgAAAAAAAAAAAASBHEAAAAAAAAAAAAkCOIAAAAAAAAAAABIEMQBAAAAAAAAAACQIIgDAAAAAAAAAAAgQRAHAAAAAAAAAABAgiAOAAAAAAAAAACABEEcAAAAAAAAAAAACYI4AAAAAAAAAAAAEgRxAAAAAAAAAAAAJAjiAAAAAAAAAAAASBDEAQAAAAAAAAAAkCCIAwAAAAAAAAAAIEEQBwAAAAAAAAAAQIIgDgAAAAAAAAAAgARBHAAAAAAAAAAAAAmCOAAAAAAAAAAAABIEcQAAAAAAAAAAACQI4gAAAAAAAAAAAEgQxAEAAAAAAAAAAJAgiAMAAAAAAAAAACBBEAcAAAAAAAAAAECCIA4AAAAAAAAAAIAEQRwAAAAAAAAAAAAJgjgAAAAAAAAAAAASBHEAAAAAAAAAAAAkCOIAAAAAAAAAAABIEMQBAAAAAAAAAACQIIgDAAAAAAAAAAAgQRAHAAAAAAAAAABAgiAOAAAAAAAAAACABEEcAAAAAAAAAAAACYI4AAAAAAAAAAAAEgRxAAAAAAAAAAAAJAjiAAAAAAAAAAAASBDEAQAAAAAAAAAAkCCIAwAAAAAAAAAAIEEQBwAAAAAAAAAAQIIgDgAAAAAAAAAAgARBHAAAAAAAAAAAAAmCOAAAAAAAAAAAABIEcQAAAAAAAAAAACQI4gAAAAAAAAAAAEgQxAEAAAAAAAAAAJAgiAMAAAAAAAAAACDhbPu9AQAAAAAAAAAAAJ55iAMAAAAAAAAAACBBEAcAAAAAAAAAAECCIA4AAAAAAAAAAIAEQRwAAAAAAAAAAAAJgjgAAAAAAAAAAAASBHEAAAAAAAAAAAAkCOIAAAAAAAAAAABIuFsqqKT4xkpGAAAAAElFTkSuQmCC"
            )
        }


        fun getDashboardWithVisualization(): Dashboard {
            return getDashboard().copy(widgets = listOf(
                    Widget(id = null, name = "name-widget"
                            , bookmarked = true,
                            visualization = hashMapOf("someParameter" to 1,
                                    "otherValue" to Position(x = 0, y = 1, rows = 2, cols = 2)),
                            app = hashMapOf("parameter" to 3),
                            type = null,
                            position = Position(x = 0, y = 1, rows = 2, cols = 2))),
                    bookmarked = true,
                    hidden = false
            )
        }


        fun getDashboardWithUknownVisualizationJsonString() = """ {
	"name": "Testing dashboard",
	"widgets": [{
		"bookmarked": false,
        "hidden":false,
		"name": "First widget",
        "visualization" : {
            "someParameter" : 1,
            "otherParameter" : 3
        },
		"position": {
			"x": 0,
			"y": 0,
			"cols": 1,
			"rows": 2
		}
	}, {
		"bookmarked": true,
		"name": "Block 1",
        "visualization" : {
            "someParameter" : 1,
            "otherParameter" : {
            "a" : 3
            }
        },
		"position": {
			"x": 1,
			"y": 0,
			"cols": 1,
			"rows": 1
		}
	}],
    "position": { "x": 0, "y": 0, "cols": 1, "rows": 2 }
    }
        """.trimIndent()
    }
}



