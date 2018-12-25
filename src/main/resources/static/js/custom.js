/*------------------
    COMPARE CART MODAL
------------------*/
$('.cmp-popup').magnificPopup({
    type: 'inline',
    removalDelay: 500,
    callbacks: {
        beforeOpen: function () {
            this.st.mainClass = this.st.el.attr('data-effect');
        }
    },
    closeMarkup: ''
}).click(function () {
    var cmpUrl = "/addToCompareCart?item=" + $(this).attr("value");
    $.ajax({
        url: cmpUrl, success: function (result) {
            $('#compare-modal > h5 > span').html(result);
        }
    });
});

/*------------------
    CART MODAL
------------------*/
$('.cart-popup').magnificPopup({
    type: 'inline',
    removalDelay: 500,
    callbacks: {
        beforeOpen: function () {
            this.st.mainClass = this.st.el.attr('data-effect');
        }
    },
    closeMarkup: ''
}).click(function () {
    var cmpUrl = "/addToCart?item=" + $(this).attr("value");
    $.ajax({
        url: cmpUrl, success: function (result) {
            $('#cart-modal > h5 > span').html(result);
            setTimeout(function () {
                location.reload();
            }, 1000);
        }
    });
});

/*------------------
    WISHLIST MODAL
------------------*/
$('.wishlist-popup').magnificPopup({
    type: 'inline',
    removalDelay: 500,
    callbacks: {
        beforeOpen: function () {
            this.st.mainClass = this.st.el.attr('data-effect');
        }
    },
    closeMarkup: ''
}).click(function () {
    var cmpUrl = "/addToWishList?item=" + $(this).attr("value");
    $.ajax({
        url: cmpUrl, success: function (result) {
            $('#wishlist-modal > #wishlist-response').html(result);
        }
    });
});

$('.remove-cart-btn').click(function () {
    var url = $(this).attr("href");
    console.log(url);
    $.ajax({
        url: url, success: function (result) {
        }
    });
    setTimeout(function () {
        location.reload();
    }, 1000);
});

/*------------------
    AJAX UPDATE CHECKOUT FRM
------------------*/
function subCheckoutFrm(object) {
    $.ajax({
        url: '/checkout.html',
        type: 'post',
        data: $(event.currentTarget).closest('form').serialize(),
        success: function () {
        }
    });
}
