def networkMap(request, format):
	""" renders a png network map, pass neato/dot/circo for layout style """
	network_links = NetworkLink.objects.all()
	dcs = {}
	# neato / dot
	networkgraph = pydot.Dot(graph_type='digraph',fontname="Verdana", layout=format, ranksep="1.5 equally", sep="1", overlap=False)
	#
	# Use pydot.Cluster to render boundary around subgraph
	# create clusters based around server rooms
	for dc in DataCenter.objects.all():
		#print("Rack %", dc.name)
		dcs[dc.name] = pydot.Cluster(dc.name,label=dc.name)
		for hw in Hardware.objects.all().filter(rack__datacenter=dc):
			if hw.has_links():
				icon = hw.hardware_model.icon
				#print("Device %s icon %s" % (hw.name, str(icon)))
				ilo = hw.ilo_address
				#print("ILO " + str(ilo))
				if ilo == None:
					ilo = ""
				else:
					ilo = str(ilo)
				if icon == None:
					dcs[dc.name].add_node(pydot.Node(hw.name, label=hw.name+"\n"+ilo, layers='all'))
				else:
					dcs[dc.name].add_node(pydot.Node(hw.name, label=hw.name+"\n"+ilo, labelloc="b", layers='all', image=settings.STATIC_ICONS+icon, shape="none", penwidth=0.2))
			else:
				#print("Device %s has no links" % hw.name)
				pass
		networkgraph.add_subgraph(dcs[dc.name])

		for hw in HardwareEnclosure.objects.all().filter(rack__datacenter=dc):
			# TODO, link up blade enclosure guest devices to the blade enclosure / blade switches. 
			if hw.has_links():
				icon = hw.hardware_model.icon
				print("Device %s icon %s" % (hw.name, str(icon)))
				ilo = hw.management_interface
				#print("ILO " + str(ilo))
				if ilo == None:
					ilo = ""
				else:
					ilo = str(ilo)
				if icon == None:
					dcs[dc.name].add_node(pydot.Node(hw.name, label=hw.name+"\n"+ilo, layers='all'))
				else:
					dcs[dc.name].add_node(pydot.Node(hw.name, label=hw.name+"\n"+ilo, labelloc="b", layers='all', image=settings.STATIC_ICONS+icon, shape="none", penwidth=0.2))
			else:
				#print("Device %s has no links" % hw.name)
				pass

	for link in network_links:
		vlans = link.get_vlans()
		label = str(link.port_0.name) + " --> " + str(link.port_1.name) + "\n" + str(len(string.replace(vlans, "\t", "")))
		if link.port_0.link_type == "ethernet10":
			width = 2
			mystyle = "dotted"
		else:
			width = 0.5
			mystyle = "solid"
		networkgraph.add_edge(pydot.Edge(link.port_0.device.name, link.port_1.device.name, label=label, layers=vlans, constraint=True, fontsize=7, len=3, penwidth=width, style=mystyle, color="red"))
	return HttpResponse(networkgraph.create_png(), 'image/png')