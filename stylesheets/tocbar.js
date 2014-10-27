/* LICENSE
 This work by Robert Zaremba (http://rz.scale-it.pl) is licensed under the Creative Commons Attribution 3.0 Unported License. To view a copy of this license, visit http://creativecommons.org/licenses/by/3.0/.
 */

var toc_button = 0;
var toc = 0;

$(document).ready(function(i) {
    toc_button = $('#toc_button');
    toc = $('#toc');

    toc_button.on('mouseover', function() {
        toc.show();
    });
    toc.on('mouseover', function() {
        toc.show();
    }).on('mouseout', function() {
        toc.hide();
    });
});

var _eid = 0;
var eid = function() { return ++_eid; };

function add_toc_entry(selectors, numerate){
    var toc_iter   = 0;
    var toc_iter_g = 0;
    var toc_col = $('<div />');
    tocelems = $(selectors);
    tocelems.each(function() {
        toc_iter   += 1;
        toc_iter_g += 1;
        var curr_elem = $(this);
        var txt = curr_elem.text();
        if(numerate)
            curr_elem.text(toc_iter_g + " " + txt);

        _id = $(this).attr('id');
        if (! _id){
            _id = 'toc_e' + eid();
            $(this).attr('id', _id);
        }

        if(toc_iter > 24){
            toc.append(toc_col);
            toc_col = $('<div />');
            toc_iter = 0;
        }
        toc_col.append($('<a></a>').attr('href', '#' + _id)
                .attr('class', this.tagName.toLowerCase())
                .html(curr_elem.text().toLowerCase())
        );
    })
    if(toc_iter)
        toc.append(toc_col);
};
