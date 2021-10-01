$(document).ready(function(){

     updateTotal();

    $(".minusButton").on("click",function(e){
        e.preventDefault();
        decreaseQuantity($(this))
    })

    $(".plusButton").on("click",function(e){
            e.preventDefault();
            increaseQuantity($(this))
            updateTotal();
    })

})

function increaseQuantity(link){
    productId = link.attr("pid");
    quantity = $("#quantity"+productId);
    quantityValNew = parseInt(quantity.val()) + 1;

    if(quantityValNew < 10){
        quantity.val(quantityValNew);
        updateQuantity(productId,1);
    }

    console.log(productId +""+ quantityValNew);
}

function decreaseQuantity(link){
    productId = link.attr("pid");
    quantity = $("#quantity"+productId);
    quantityValNew = parseInt(quantity.val()) - 1;

    if(quantityValNew > 0){
        quantity.val(quantityValNew);
        updateQuantity(productId,-1);
    }



    console.log(productId + quantityValNew);
}

function updateTotal(){
    total = 0.0;
    $(".productSubtotal").each(function(index, element){
        total += parseFloat(element.innerHTML);
    })

    $(".totalAmount").text(total);
}

function updateQuantity(productId,qty){
    url = $("input[name='contextPath']").val() + "api/cart/update/"+productId+"/"+qty;
    csrfHeaderName = $("input[name='csrfHeaderName']").val();
    csrfValue = $("input[name='csrfToken']").val();
    console.log(url+" "+csrfHeaderName+csrfValue);

    $.ajax({
        type:"POST",
        url: url,
        beforeSend: function(xhr){
            xhr.setRequestHeader(csrfHeaderName,csrfValue);
        }
    }).done(function(res){
        $("#"+res.id).text(res.subTotal)
        updateTotal();
    })
}