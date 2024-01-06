<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 1/2/2024
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>


<!-- Footer -->
</div>
</div>
</body>
<script>
    function handleActiveMenu(){
        const uls = document.getElementsByClassName("item");

        for(let i = 0; i < uls.length; i++){
            uls[i].classList.add("hidden")
            if(location.href.includes(uls[i].id)){
                uls[i].classList.remove("hidden");
            }
        }

        if(!location.href.includes("act")){
            document.getElementById("dashboard").classList.remove("hidden");
        }
    }
    handleActiveMenu();
</script>
</html>
