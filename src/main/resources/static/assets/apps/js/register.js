// let mtTableClassName = "mt-table-field";
// let mtButton;
// let countTbody;
//
// let dataPemegang = [];
// let dataPenanggung = [];
//
// let popup = $('#modal-popup-mt');
//
// $(document).on('click', '.btn-popup-mt', function(e) {
//
//     mtButton = $(this);
//
//     // Set title
//     popup.find("#title-legend").text($(this).data('popup-title'));
//     popup.modal('show');
// });
//
// $(document).on('submit', '#formPemegang', function(e) {
//     e.preventDefault();
//
//     let dataForm = new FormData($(this)[0]);
//     let idButton = mtButton.data('id');
//     let varName;
//     let aliasPrefix;
//
//     if (idButton == 'pemegangSaham') {
//         varName = 'pemegang_saham';
//     } else if (idButton == 'penanggungJawab') {
//         varName = 'penanggung_jawab';
//     };
//
//     let data = {
//         [`nama_${varName}`]: dataForm.get("mt_nama"),
//         [`jabatan_${varName}`]: dataForm.get("mt_jabatan"),
//         [`npwp_${varName}`]: dataForm.get("mt_npwp"),
//         [`email_${varName}`]: dataForm.get("mt_email"),
//         [`alamat_${varName}`]: dataForm.get("mt_alamat"),
//     };
//
//     if (idButton == 'pemegangSaham') {
//         dataPemegang.push(data);
//         aliasPrefix = 'ps';
//     } else if (idButton == 'penanggungJawab') {
//         dataPenanggung.push(data);
//         aliasPrefix = 'pj';
//     };
//
//     let mt_tbody = `<tr>`;
//     mt_tbody += `<td class='${aliasPrefix}-nama'>${$(this).find('#PS-Nama').val()}</td>`;
//     mt_tbody += `<td>${$(this).find('#PS-Jabatan').val()}</td>`;
//     mt_tbody += `<td>${$(this).find('#PS-Npwp').val()}</td>`;
//     mt_tbody += `<td>${$(this).find('#PS-Alamat').val()}</td>`;
//     mt_tbody += `<td>
//             <button type='button' class='btn btn-danger btn-delete-mt btn-xs' data-alias='${aliasPrefix}'>
//                 <i class='fa fa-minus'></i></button>
//         </td>`;
//     mt_tbody += `</tr>`;
//
//     mtButton.parent().next().find(`.${mtTableClassName} > tbody:last`).append(mt_tbody);
//     popup.modal('hide');
//
//     $(this)[0].reset();
// })
//
// $(document).on('click', '.btn-delete-mt', function(e) {
//     e.preventDefault();
//
//     let alias = $(this).data('alias');
//     let row = $(this).parent().parent();
//     let nama = row.find(`.${alias}-nama`);
//
//     if (alias == 'ps') {
//         let index = findIndexByKeyValue(dataPemegang, 'nama_pemegang_saham', nama.text());
//         dataPemegang.splice(index, 1);
//
//     } else if (alias == 'pj') {
//         let index = findIndexByKeyValue(dataPenanggung, 'nama_penanggung_jawab', nama.text());
//         dataPenanggung.splice(index, 1);
//
//     }
//
//     row.remove();
// })
//
// function findIndexByKeyValue(arraytosearch, key, valuetosearch) {
//     for (var i = 0; i < arraytosearch.length; i++) {
//         if (arraytosearch[i][key] == valuetosearch) {
//             return i;
//         }
//     }
//
//     return null;
// }
// $(document).ready(function() {
//     // Set message error by label
//     $(".invalid-feedback").each(function(val, key) {
//         let label = $(this).parent().find("label").text();
//         $(this).text(`${label} mohon diisi.`);
//     })
// });
//
// $(document).on("click", "#btn-register", function(e) {
//     var form = $("#regis_manual")[0];
//     var isValid = form.checkValidity();
//     if (!isValid) {
//         e.preventDefault();
//         e.stopPropagation();
//     }
//     form.classList.add('was-validated');
// });
//
// let baseUrlHubla = "https://sehatitest.hubla.dephub.go.id";
// var token = "c3d3a1db-22b6-3173-a968-07df76b3df06";
// let version = "";
// let ids = {
//     provinsiId: ['ProvinsiPerseroan', 'PS-Provinsi'],
//     kotaKabId: ['KotaKabupaten', 'PS-KotaKabupaten'],
//     kecamatanId: ['KecamatanPerseroan', 'PS-Kecamatan'],
//     kelurahanId: ['KelurahanPerseroan', 'PS-Kelurahan'],
// };
//
// function setToken() {
//     let token = localStorage.getItem('hublaToken');
//     let expiredToken = new Date(localStorage.getItem('hublaExpiresIn'));
//     let now = new Date;
//
//     if (
//         !token
//         || (expiredToken.getTime() < now.getTime())
//     ) {
//         $.ajax({
//             url: '/token/generate',
//             type: 'get',
//             dataType: 'json',
//             success: function(res) {
//                 let expiredDate = new Date();
//                 expiredDate.setSeconds(expiredDate.getSeconds() + (res.data.expires_in - 10));
//
//                 localStorage.setItem('hublaToken', res.data.access_token);
//                 localStorage.setItem('hublaExpiresIn', expiredDate);
//             }
//         })
//     }
// }
//
// $(document).ready(function() {
//     // setToken();
//
//     // INIT PROVINSI
//     $.each(ids.provinsiId, function(index, val) {
//         getWilayah('/apis/data/provinsi', val, {
//             id: "KODE_PROVINSI",
//             value: "NAMA_PROVINSI"
//         }, {}, '- Pilih Provinsi -');
//     })
// })
//
// // INIT KOTA KABUPATEN
// $.each(ids.kotaKabId, function(index, val) {
//     $(document).on('change', `#${ids.provinsiId[index]}`, function(e) {
//         let idProvinsi = $(this).val();
//
//         if (idProvinsi)
//             getWilayah(`/apis/data${version}/kabkota`, val, {
//                 id: "KODE_KABKOTA",
//                 value: "NAMA_KABKOTA"
//             }, {p: idProvinsi}, '- Pilih Kota/Kabupaten -');
//         else
//             setDropdown({}, val, '- Pilih Kota/Kabupaten -');
//     })
// })
//
// // INIT KECAMATAN
// $.each(ids.kecamatanId, function(index, val) {
//     $(document).on('change', `#${ids.kotaKabId[index]}`, function(e) {
//         let idKabupaten = $(this).val();
//
//         if (idKabupaten)
//             getWilayah(`/apis/data${version}/kecamatan`, val, {
//                 id: "KODE_KECAMATAN",
//                 value: "NAMA_KECAMATAN"
//             }, {p: idKabupaten}, '- Pilih Kecamatan -');
//         else
//             setDropdown({}, val, '- Pilih Kecamatan -');
//     })
// })
//
// // INIT KELURAHAN
// $.each(ids.kelurahanId, function(index, val) {
//     $(document).on('change', `#${ids.kecamatanId[index]}`, function(e) {
//         let idKecamatan = $(this).val();
//
//         //     if (idKecamatan)
//         //         getWilayah(`/apis/data${version}/kelurahan`, val, {
//         //             id: "KODE_KELURAHAN",
//         //             value: "NAMA_KELURAHAN"
//         //         }, {p: idKecamatan}, '- Pilih Kelurahan -');
//         //     else
//         //         setDropdown({}, val, '- Pilih Kelurahan -');
//
//         if (idKecamatan) {
//             if ($(`#${val}`).data('select2'))
//                 $(`#${val}`).select2('destroy');
//
//             $(`#${val}`).select2({
//                 minimumInputLength: 3,
//                 allowClear: true,
//                 placeholder: 'Cari Nama Kelurahan',
//                 dropdownParent: $(`#${val}`).parent(),
//                 height: '37px',
//                 ajax: {
//                     dataType: 'json',
//                     url: `${baseUrlHubla}/apis/data${version}/kelurahan`,
//                     delay: 800,
//                     beforeSend: function (xhr) {
//                         // xhr.setRequestHeader('Authorization', `Bearer ${localStorage.getItem('hublaToken')}`);
//                         xhr.setRequestHeader('Authorization', `Bearer ${token}`);
//                     },
//                     data: function(params) {
//                         return {
//                             q: params.term,
//                             p: idKecamatan
//                         }
//                     },
//                     processResults: function (res, page) {
//                         let kelurahans = [];
//
//                         $.each(res.data, function(key, val) {
//                             kelurahans[key] = {id: val.KODE_KELURAHAN, text: val.NAMA_KELURAHAN};
//                         });
//
//                         console.log(kelurahans)
//
//                         return {
//                             results: kelurahans
//                         };
//                     },
//                 }
//             });
//         }
//     })
//
// })
//
//
// function getWilayah(path, idDropdown, propSet, params = {}, label = '- Pilih -') {
//     $.ajax({
//         url: `${baseUrlHubla}${path}`,
//         type: 'GET',
//         dataType: 'json',
//         beforeSend: function (xhr) {
//             // xhr.setRequestHeader('Authorization', `Bearer ${localStorage.getItem('hublaToken')}`);
//             xhr.setRequestHeader('Authorization', `Bearer ${token}`);
//         },
//         data: params
//     })
//         .done(function(res) {
//             let data = [];
//
//             $.each(res.data, function(key, val) {
//                 let id = val[propSet.id];
//                 let value = val[propSet.value];
//
//                 data[id] = value;
//             });
//
//             setDropdown(data, idDropdown, label);
//         })
//         .fail(function() {
//             console.log("error");
//         });
// }
//
// // START SET DROPDOWN
// function setDropdown(data, id, label = '- Pilih -', $default = null) {
//     let dropdown = $(`#${id}`);
//     dropdown.empty();
//
//     dropdown.append(`<option value="">${label}</option>`);
//     $.each(data, function(index, val) {
//
//         if (typeof val === 'object') {
//             if (val.length == 0) return;
//
//             let opts = `<optgroup label="${index}">`;
//
//             $.each(val, function(index1, val1) {
//                 let selected2 = '';
//                 if ($default == index) {
//                     selected2 = 'selected';
//                 }
//
//                 opts += `<option value="${index1}" ${selected2}>${val1}</option>`;
//             })
//
//             opts += `</optgroup>`;
//
//             dropdown.append(opts);
//
//         } else {
//             let selected = '';
//             if ($default == index) {
//                 selected = 'selected';
//             }
//
//             if (val)
//                 dropdown.append(`<option value="${index}" ${selected}>${val}</option>`);
//         }
//     });
//     dropdown.trigger('change');
// }
// // END SET DROPDOWN
